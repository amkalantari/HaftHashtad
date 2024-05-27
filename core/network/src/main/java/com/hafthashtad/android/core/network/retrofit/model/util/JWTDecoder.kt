package com.hafthashtad.android.core.network.retrofit.model.util

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object JWTDecoder {

//    fun decodePermissions(networkJson: Json, token: String): List<AppPermission> {
//        val decodedData =
//            String(Base64.decode(token.split(".")[1], Base64.DEFAULT), StandardCharsets.UTF_8)
//        val jwtDecoded = networkJson.decodeFromString<JWTDecoded>(decodedData)
//        return jwtDecoded.getPermissionsList(networkJson).groupBy {
//            it.pageTitle
//        }.map {
//            val page = PermissionPage.getPermissionPage(it.key)
//            val components =
//                PermissionComponent.getComponents(page, it.value.map(Permission::sectionTitle))
//            AppPermission(
//                page = page,
//                components = components
//            )
//        }
//    }
}

@Serializable
data class JWTDecoded(
    @SerialName("Permissions")
    val permissions: String
) {

    fun getPermissionsList(networkJson: Json) =
        networkJson.decodeFromString<List<Permission>>(permissions)
}

@Serializable
data class Permission(
    @SerialName("pageTitle")
    val pageTitle: String,
    @SerialName("sectionTitle")
    val sectionTitle: String
)
