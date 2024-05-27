package com.hafthashtad.android.core.data.repository.offline

import com.hafthashtad.android.core.data.model.DarkThemeConfig
import com.hafthashtad.android.core.data.model.ThemeBrand
import com.hafthashtad.android.core.data.model.UserData
import com.hafthashtad.android.core.data.repository.HafthashtadUserDataRepository
import com.hafthashtad.android.core.datastore.HafthashtadPreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineHafthashtadUserDataRepository @Inject constructor(
    private val hafthashtadPreferencesDataSource: HafthashtadPreferencesDataSource
) : HafthashtadUserDataRepository {

    override val userDataStream: Flow<UserData> =
        hafthashtadPreferencesDataSource.userDataStream

    override suspend fun setThemeBrand(themeBrand: ThemeBrand) =
        hafthashtadPreferencesDataSource.setThemeBrand(themeBrand)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        hafthashtadPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)

    override fun getDarkThemeConfig(): Flow<DarkThemeConfig> =
        hafthashtadPreferencesDataSource.getDarkThemeConfig()

    override suspend fun clearUserData() =
        hafthashtadPreferencesDataSource.clearUserData()

    override suspend fun hasLogin() =
        hafthashtadPreferencesDataSource.hasLogin()

    override suspend fun getAccessToken(): String? =
        hafthashtadPreferencesDataSource.getAccessToken()
}
