package com.hafthashtad.android.feature.home

import androidx.lifecycle.viewModelScope
import com.hafthashtad.android.core.data.common.result.Result
import com.hafthashtad.android.core.data.common.result.asResult
import com.hafthashtad.android.core.domain.hafthashtad.GetProductsUseCase
import com.hafthashtad.android.core.ui.BaseViewModel
import com.hafthashtad.android.feature.home.HomeContract.Effect
import com.hafthashtad.android.feature.home.HomeContract.Event
import com.hafthashtad.android.feature.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getProductsUseCase: GetProductsUseCase
) : BaseViewModel<Event, UiState, Effect>() {

    private val _query = MutableStateFlow("")

    val query: StateFlow<String> = _query

    val productList = combine(
        getProductsUseCase().asResult(),
        _query
    ) { res, query ->
        when (res) {
            is Result.Error -> {
                setEffect {
                    Effect.ShowError(res.errorMsg)
                }
                UiState.Failure(res.errorMsg)
            }

            Result.Loading -> {
                UiState.Loading
            }

            is Result.Success -> {
                UiState.Success(res.data.filter {
                    it.title.contains(query, ignoreCase = true)
                })
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_00),
        HomeContract.initValue
    )

    override fun setInitialViewState() = HomeContract.initValue

    override fun handleEvents(event: Event) = when (event) {
        is Event.Search -> {
            onQueryChanged(event.query)
        }
    }

    private fun onQueryChanged(query: String) {
        _query.value = query
    }
}