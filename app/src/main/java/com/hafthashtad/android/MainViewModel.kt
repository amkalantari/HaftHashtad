package com.hafthashtad.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.hafthashtad.android.core.data.model.UserData
import com.hafthashtad.android.core.data.repository.HafthashtadUserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val application: Application,
    private val userDataRepository: HafthashtadUserDataRepository,
) : AndroidViewModel(application) {

    val uiState = userDataRepository.userDataStream.map {
        MainActivityUiState.Success(
            userData = it,
            isLogin = userDataRepository.hasLogin(),
            account = GoogleSignIn.getLastSignedInAccount(application)
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000)
    )
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(
        val userData: UserData,
        val isLogin: Boolean,
        val account: GoogleSignInAccount?,
    ) : MainActivityUiState
}
