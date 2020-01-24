package com.target.businesslogic.core.functional

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 *  Either type doesn't do anything on its own. Its a placeholder for to return a type of
 *  Left or Right Which is not decided yet.
 */
sealed class Either<out L, out R> {

    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>

    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Either.Left(a)

    fun <R> right(b: R) = Either.Right(b)


    /**
     * This function shall return one of the function in the argument.
     * We will go with assumption that anything on left is a function when things were to fail.
     * And function on the right should take place when action (task) was successful!
     */
    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

}