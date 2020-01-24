package com.target.businesslogic.feature

import com.target.businesslogic.core.exception.Failure
import com.target.businesslogic.model.Product
import com.target.businesslogic.network.RetrofitManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class ProductDealsUseCaseTest {

    private lateinit var retrofit: Retrofit
    private lateinit var productDealsUseCase: ProductDealsUseCase

    @Before
    fun setUp() {

        retrofit = RetrofitManager().buildWithBaseUrl("http://target-deals.herokuapp.com/")

        val viewModelJob = Job()
        val coroutineScope = CoroutineScope((Dispatchers.IO + viewModelJob))

        productDealsUseCase =
            object : ProductDealsUseCase(retrofit, coroutineScope, Dispatchers.IO) {}
    }

    /**
     * Just wanted to make sure code in this layer is actually working
     */
    @Test
    fun makeApiCall() {
        val params = ProductDealsUseCase.Params("api/deals")
        productDealsUseCase(params) { result ->
            result.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Product>) {
        println("list size: ${list.size}")
    }

    private fun handleFailure(failure: Failure) {

    }
}