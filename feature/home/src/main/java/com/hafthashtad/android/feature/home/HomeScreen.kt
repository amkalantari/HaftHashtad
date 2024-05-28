package com.hafthashtad.android.feature.home

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hafthashtad.android.core.designsystem.theme.HafthashtadBackground
import com.hafthashtad.android.core.designsystem.theme.HafthashtadTheme
import com.hafthashtad.android.core.ui.DevicePreviews
import com.hafthashtad.android.core.ui.FailureOrEmptyHafthashtad
import com.hafthashtad.android.core.ui.HafthashtadLoading
import com.hafthashtad.android.feature.home.HomeContract.UiState
import com.hafthashtad.android.feature.home.ui.ProductsItem
import kotlinx.coroutines.flow.onEach

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

    val uiState by viewModel.viewState.collectAsStateWithLifecycle()

    HomeScreenContent(uiState = uiState, onEventSent = onEventSent)

}

@Composable
fun HomeScreenContent(uiState: UiState, onEventSent: (event: HomeContract.Event) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        when (uiState) {
            is UiState.Failure -> {
                FailureOrEmptyHafthashtad(message = uiState.msg, onRefreshClick = {
                    onEventSent.invoke(HomeContract.Event.Refresh)
                })
            }

            UiState.Loading -> {
                HafthashtadLoading(modifier = Modifier.fillMaxSize())
            }

            is UiState.Success -> {
                LazyColumn {
                    items(uiState.data) {
                        ProductsItem(item = it)
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
            HomeScreenContent(uiState = HomeContract.initValue, onEventSent = {})
        }
    }
}
