package com.hafthashtad.android.core.data.repository

import com.hafthashtad.android.core.data.model.DarkThemeConfig
import com.hafthashtad.android.core.data.model.ThemeBrand
import com.hafthashtad.android.core.data.model.UserData
import kotlinx.coroutines.flow.Flow

interface HafthashtadUserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userDataStream: Flow<UserData>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    fun getDarkThemeConfig(): Flow<DarkThemeConfig>

    /**
     * Clears the user's data
     */
    suspend fun clearUserData()

    /**
     * Checks the user has login data
     */
    suspend fun hasLogin(): Boolean

    suspend fun getAccessToken(): String?
}
