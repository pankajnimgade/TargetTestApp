package com.target.businesslogic.network

import com.target.businesslogic.api.ProductDealsAPI
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
class RetrofitManagerTest {

    private lateinit var retrofit: Retrofit
    private lateinit var productDealsAPI: ProductDealsAPI

    @Before
    fun setUp() {

        retrofit = RetrofitManager().buildWithBaseUrl("http://target-deals.herokuapp.com/")
        productDealsAPI = retrofit.create(ProductDealsAPI::class.java)
    }

    /**
     * Just wanted to make sure code in this layer is actually working
     */
    @Test
    fun makeApiCall() {
        val response = productDealsAPI.getProductDeals("api/deals").execute()

        println("response.isSuccessful: ${response.isSuccessful}")
        println("response.isSuccessful: ${response.message()}")
        println("response.isSuccessful: ${response.body()?._id}")
        println("response.isSuccessful: ${response.body()?.data?.size}")
        println("response.isSuccessful: ${response.body()?.type}")
    }
}