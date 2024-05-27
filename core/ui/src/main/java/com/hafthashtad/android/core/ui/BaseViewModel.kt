package com.hafthashtad.android.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafthashtad.android.core.designsystem.component.SnackBarType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : ViewEvent, UiState : ViewState, Effect : ViewSideEffect> :
    ViewModel() {

    private val initialState: UiState by lazy { setInitialViewState() }
    abstract fun setInitialViewState(): UiState

    private val _viewState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val viewState: StateFlow<UiState> = _viewState

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeToEvents()
    }

    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun StateFlow<UiState>.collectAsUIState() {
        viewModelScope.launch {
            this@collectAsUIState.collect {
                setState {
                    it
                }
            }
        }
    }

    protected fun setState(reducer: UiState.() -> UiState) {
        val newState = viewState.value.reducer()
        viewModelScope.launch { _viewState.emit(newState) }
    }

    protected fun MutableStateFlow<UiState>.setState(reducer: UiState.() -> UiState) {
        val newState = value.reducer()
        viewModelScope.launch { emit(newState) }
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    abstract fun handleEvents(event: Event)

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}

interface ViewState

interface ViewEvent

interface ViewSideEffect

open class BaseShowSnack constructor(
    open val type: SnackBarType,
    open val title: String,
    open val message: String,
    open val positiveTitle: String,
    open val negativeTitle: String? = null
)
