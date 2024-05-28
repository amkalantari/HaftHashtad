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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<Event, UiState, Effect>() {

    init {
        getProductsList()
    }

    private fun getProductsList() {
        getProductsUseCase().asResult().map { res ->
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
                    UiState.Success(res.data)
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_00),
            HomeContract.initValue
        ).collectAsUIState()
    }

    override fun setInitialViewState() = HomeContract.initValue

    override fun handleEvents(event: Event) = when (event) {
        Event.Search -> {

        }

        Event.Refresh -> {
            getProductsList()
        }
    }
}