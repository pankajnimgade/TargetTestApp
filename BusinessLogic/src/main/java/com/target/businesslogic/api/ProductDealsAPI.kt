package com.target.businesslogic.api

import com.target.businesslogic.response.ProductDealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
interface ProductDealsAPI {

    @GET
    fun getProductDeals(@Url dealsUrl: String): Call<ProductDealsResponse>
}