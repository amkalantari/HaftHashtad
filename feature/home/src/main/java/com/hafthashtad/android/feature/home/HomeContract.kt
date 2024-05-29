package com.hafthashtad.android.feature.home

import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.ui.ViewEvent
import com.hafthashtad.android.core.ui.ViewSideEffect
import com.hafthashtad.android.core.ui.ViewState

class HomeContract {

    companion object {
        val initValue = UiState.Loading
    }

    sealed interface UiState : ViewState {
        data class Success(
            val data: List<Products>
        ) : UiState

        data object Loading : UiState
        data class Failure(val msg: String) : UiState
    }

    sealed interface Event : ViewEvent {
        data class Search(val query: String) : Event
        data object Refresh : Event

    }

    sealed interface Effect : ViewSideEffect {
        data class ShowError(var msg: String) : Effect
    }
}
