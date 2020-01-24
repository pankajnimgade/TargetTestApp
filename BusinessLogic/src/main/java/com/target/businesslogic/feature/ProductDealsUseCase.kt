package com.target.businesslogic.feature

import com.target.businesslogic.api.ProductDealsAPI
import com.target.businesslogic.core.exception.Failure
import com.target.businesslogic.core.functional.Either
import com.target.businesslogic.core.interactor.UseCase
import com.target.businesslogic.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit

/**
 * Created by Pankaj Nimgade on 1/19/2020.
 */
abstract class ProductDealsUseCase (
    val retrofit: Retrofit,
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher
) : UseCase<List<Product>, ProductDealsUseCase.Params>(scope, dispatcher) {

    data class Params(val dealsUrl: String)

    /**
     * If there is an error specific to this Use case, throw this error
     */
//    class ProductDealsError(error: String) : Failure.UseCaseFailure()

    abstract fun isNetworkAvailable(): Boolean

    override suspend fun run(params: Params): Either<Failure, List<Product>> {

        if (!isNetworkAvailable()) {
            return Either.Left(Failure.NetworkConnection)
        }

        val productDealsAPI = retrofit.create(ProductDealsAPI::class.java)
        val response = try {
            productDealsAPI.getProductDeals(params.dealsUrl).execute()
        } catch (e: Exception) {
            e.printStackTrace()
            return Either.Left(Failure.ServerError)
        }

        response?.body()?.let {
            it.data?.let { productList ->
                if (productList.isNotEmpty()) {
                    return Either.Right(productList)
                }
            }
        }

        return Either.Left(Failure.NetworkConnection)
    }
}