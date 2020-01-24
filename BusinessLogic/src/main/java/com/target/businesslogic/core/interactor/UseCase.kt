package com.target.businesslogic.core.interactor

import com.target.businesslogic.core.exception.Failure
import com.target.businesslogic.core.functional.Either
import kotlinx.coroutines.*

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params>(
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) where Type : Any {

    private lateinit var onResult: (Either<Failure, Type>) -> Unit

    /**
     * Methods to be executed in another scope (coroutines...)
     */
    protected abstract suspend fun run(params: Params): Either<Failure, Type>

    /**
     * Only method which needs to be called
     */
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        this.onResult = onResult
        scope.launch {
            val result = run(params = params)
            withContext(dispatcher) { onResult(result) }
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun status(result: Any) {
        onResult(result as Either<Failure, Type>)
    }

    fun cancel(cause: CancellationException) {
        scope.cancel(cause)
    }
}