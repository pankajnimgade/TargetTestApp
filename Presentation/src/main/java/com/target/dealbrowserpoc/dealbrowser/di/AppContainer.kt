package com.target.dealbrowserpoc.dealbrowser.di

import com.target.businesslogic.network.RetrofitManager
import com.target.framework.use.case.ProductDealsUseCaseImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Pankaj Nimgade on 1/21/2020.
 */
class AppContainer(private val baseUrl: String, isDebug: Boolean) {

    private val retrofitManager = RetrofitManager()

    private val retrofit = retrofitManager.buildWithBaseUrl(baseUrl, isDebug)

    private val scope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO + Job()
    }

    private val coroutineDispatcher = Dispatchers.Main

    val productDealsUseCaseImpl =
        ProductDealsUseCaseImpl(retrofit, scope, coroutineDispatcher)

}