package fr.systemathicdev.commons.extensions

import fr.systemathicdev.commons.tools.AppResult
import kotlinx.coroutines.flow.*
import java.lang.Exception

suspend fun <T> Flow<AppResult<T>>.getLastSuccessDataOrNull(): T?{
    return this.lastOrNull()?.getSuccessDataOrNull()
}

suspend fun <T> Flow<AppResult<T>>.getLastErrorOrNull(): AppResult.Error<T>?{
    return this.lastOrNull()?.getErrorOrNull()
}