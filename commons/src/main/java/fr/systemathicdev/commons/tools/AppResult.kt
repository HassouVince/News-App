package fr.systemathicdev.commons.tools

import java.util.concurrent.atomic.AtomicBoolean

sealed class AppResult<T> {
    data class Success<T>(val data: T) : AppResult<T>()
    class Error<T>(val throwable: Throwable) : AppResult<T>(){
        override fun toString(): String {
            return "Error(throwable=$throwable)"
        }
    }
    class Loading<T> : AppResult<T>()

    private val consumed = AtomicBoolean(false)
    fun consume(block: () -> Unit){
        if(!consumed.getAndSet(true)){
            block()
        }
    }

    override fun toString(): String {
        return "AppResult(consumed=$consumed)"
    }
}