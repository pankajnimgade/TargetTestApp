package com.target.framework.use.case

import com.target.businesslogic.feature.ProductDealsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class ProductDealsUseCaseImpl(
    retrofit: Retrofit,
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher
) : ProductDealsUseCase(retrofit, scope, dispatcher) {

    override fun isNetworkAvailable(): Boolean {
        //TODO need to implement this to check for network call
        // this was done to show-case the delegation of BusinessLogic code to framework related
        // behaviour
        return true
    }


}