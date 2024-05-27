package com.hafthashtad.android.feature.home

import com.hafthashtad.android.core.domain.hafthashtad.GetProductsUseCase
import com.hafthashtad.android.core.ui.BaseViewModel
import com.hafthashtad.android.feature.home.HomeContract.Effect
import com.hafthashtad.android.feature.home.HomeContract.Event
import com.hafthashtad.android.feature.home.HomeContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<Event, UiState, Effect>() {

    init {

    }

    override fun setInitialViewState() = HomeContract.initValue

    override fun handleEvents(event: Event) = when (event) {
        Event.Search -> {

        }

        Event.Refresh -> {

        }
    }
}