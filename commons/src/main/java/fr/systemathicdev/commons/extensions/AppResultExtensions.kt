package fr.systemathicdev.commons.extensions

import fr.systemathicdev.commons.tools.AppResult

fun <T> AppResult<T>.getSuccessDataOrNull(): T? {
    return (this as? AppResult.Success<T>)?.data
}

fun <T> AppResult<T>.getErrorOrNull(): AppResult.Error<T>? {
    return (this as? AppResult.Error<T>)
}

fun <T> AppResult<T>.onSuccess(action: (T) -> Unit) = when(this){
    is AppResult.Success -> apply { action(data) }
    is AppResult.Error -> this
    is AppResult.Loading -> this
}

fun <T> AppResult<T>.onError(action: (Throwable) -> Unit) = when(this){
    is AppResult.Success -> this
    is AppResult.Error -> apply { action(throwable) }
    is AppResult.Loading -> this
}

fun <T> AppResult<T>.onLoading(action: () -> Unit) = when(this){
    is AppResult.Success -> this
    is AppResult.Error -> this
    is AppResult.Loading -> apply { action() }
}