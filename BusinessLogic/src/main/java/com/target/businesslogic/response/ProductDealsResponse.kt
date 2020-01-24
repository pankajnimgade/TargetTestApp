package com.target.businesslogic.response

import com.target.businesslogic.model.Product

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
data class ProductDealsResponse(
    val _id: String? = null,
    val data: List<Product>? = null,
    val type: String? = null
)