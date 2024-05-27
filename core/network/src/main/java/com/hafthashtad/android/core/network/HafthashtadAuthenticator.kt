package com.hafthashtad.android.core.network

import android.util.Log
import com.hafthashtad.android.core.datastore.HafthashtadPreferencesDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class HafthashtadAuthenticator @Inject constructor(
    private val hafthashtadPreferencesDataSource: HafthashtadPreferencesDataSource,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? = runBlocking {

        hafthashtadPreferencesDataSource.clearUserData()
        Log.d("Authenticator", "authenticate: return")
        return@runBlocking null
    }
}