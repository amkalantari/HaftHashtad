package com.hafthashtad.android.feature.home

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hafthashtad.android.core.designsystem.R
import com.hafthashtad.android.core.designsystem.component.HafthashtadSearchBar
import com.hafthashtad.android.core.designsystem.component.LeftTitleTopAppBar
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme
import com.hafthashtad.android.core.ui.DevicePreviews
import com.hafthashtad.android.core.ui.HafthashtadFailure
import com.hafthashtad.android.core.ui.HafthashtadLoading
import com.hafthashtad.android.feature.home.HomeContract.UiState
import com.hafthashtad.android.feature.home.ui.ProductsItem
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val onEventSent = viewModel::handleEvents
    val context = LocalContext.current

    LaunchedEffect(context, viewModel.effect) {
        viewModel.effect.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.ShowError -> {
                    Toast.makeText(context, effect.msg, LENGTH_SHORT).show()
                }
            }
        }.collect {}
    }

    val uiState by viewModel.productList.collectAsStateWithLifecycle()
    val query by viewModel.query.collectAsStateWithLifecycle()

    Column {

        LeftTitleTopAppBar(
            title = stringResource(id = R.string.app_name)
        )

        HomeScreenContent(uiState = uiState, query = query, onEventSent = onEventSent)

    }

}

@Composable
fun HomeScreenContent(
    uiState: UiState, query: String, onEventSent: (event: HomeContract.Event) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is UiState.Failure -> {
                HafthashtadFailure(message = uiState.msg) {
                    onEventSent.invoke(HomeContract.Event.Refresh)
                }
            }

            UiState.Loading -> {
                HafthashtadLoading(modifier = Modifier.fillMaxSize())
            }

            is UiState.Success -> {
                Column(modifier = Modifier.fillMaxSize()) {

                    HafthashtadSearchBar(value = query) {
                        onEventSent.invoke(HomeContract.Event.Search(it))
                    }

                    LazyColumn {
                        items(uiState.data, key = { it.id }) {
                            ProductsItem(product = it)
                        }
                    }

                }
            }
        }
    }
}


@DevicePreviews
@Composable
fun PreviewHomeScreenContent() {
    HafthashtadTheme {
        HafthashtadBackground {
            HomeScreenContent(uiState = HomeContract.initValue, query = "query", onEventSent = {})
        }
    }
}
