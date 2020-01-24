package com.target.businesslogic.core.exception

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
sealed class Failure(val error: String = "Failure") {

    /**
     * When device has no internet connection throw this error
     */
    object NetworkConnection : Failure()

    /**
     * When API call fails to return a valid response throw this error
     */
    object ServerError : Failure()

    /**
     *  Extend this class for feature specific failures.
     */
    abstract class UseCaseFailure : Failure()
}