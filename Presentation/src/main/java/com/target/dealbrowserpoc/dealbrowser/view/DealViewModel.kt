package com.target.dealbrowserpoc.dealbrowser.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.target.businesslogic.core.exception.Failure
import com.target.businesslogic.feature.ProductDealsUseCase
import com.target.businesslogic.model.Product
import com.target.dealbrowserpoc.dealbrowser.BuildConfig
import com.target.framework.use.case.ProductDealsUseCaseImpl

/**
 * Created by Pankaj Nimgade on 1/21/2020.
 */
class DealViewModel(
    private val productDealsUseCaseImpl: ProductDealsUseCaseImpl
) : ViewModel() {

    companion object {
        private val TAG = DealViewModel::class.java.simpleName
    }

    private val productList: MutableLiveData<List<Product>> = MutableLiveData()

    val errorPublisher: MutableLiveData<Failure> = MutableLiveData()

    fun loadData(): MutableLiveData<List<Product>> {
        if (productList.value?.isEmpty() ?: true) { //
            refreshData()
        }
        return productList
    }

    private fun refreshData() {

        val params = ProductDealsUseCase.Params(BuildConfig.API_DEALS)
        productDealsUseCaseImpl(params) { result ->
            result.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(list: List<Product>?) {
        list?.let {
            if ((it.isNotEmpty())) {
                productList.postValue(list)
            }
        }
    }

    private fun handleFailure(failure: Failure) {
        errorPublisher.postValue(failure)
    }
}