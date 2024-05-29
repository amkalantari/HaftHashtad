package com.hafthashtad.android.core.data.common.result

import android.database.sqlite.SQLiteConstraintException
import com.hafthashtad.android.core.data.common.result.Result.Loading
import com.hafthashtad.android.core.data.common.result.Result.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null, val errorMsg: String = "An error occurred") :
        Result<Nothing>

    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Success(it)
        }
        .onStart { emit(Loading) }
        .catch {
            emit(handleError(it))
        }
}

fun handleError(t: Throwable?): Result.Error {
    return when (t) {
        is EOFException -> Result.Error(errorMsg = "EOF Exception", exception = t)
        is IOException -> Result.Error(
            errorMsg = "Please Check Your Internet Connection.",
            exception = t
        )

        is SQLiteConstraintException -> Result.Error(errorMsg = "SQL Exception", exception = t)
        is HttpException -> {
            Result.Error(
                errorMsg = t.response()?.errorBody().toString(),
                exception = t
            )
        }

        else -> Result.Error(errorMsg = "Undefine", exception = t)

    }

}