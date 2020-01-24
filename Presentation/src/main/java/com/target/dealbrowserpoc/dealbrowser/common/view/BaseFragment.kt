package com.target.dealbrowserpoc.dealbrowser.common.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.target.dealbrowserpoc.dealbrowser.BuildConfig
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.TargetApplication
import com.target.dealbrowserpoc.dealbrowser.view.DealViewModel

/**
 * Created by Pankaj Nimgade on 1/21/2020.
 */
abstract class BaseFragment : Fragment() {

    companion object {
        // this shall help distinguish logs between Fragment vs Activity
        private val TAG = BaseFragment::class.java.simpleName
    }

    protected var isTablet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAppComponent()
        checkIfDeviceIsTablet()
    }

    /**
     * Dependency Inject with dagger -2 needed
     */
    private fun initializeAppComponent() {}

    private fun checkIfDeviceIsTablet() {
        context?.let {
            isTablet = it.resources.getBoolean(R.bool.isTablet)
        }
    }

    /**
     * This is should be used for [Fragment] level logging
     */
    protected fun logMessage(componentName: String, message: String) {
        // A friendly check in case forgotten to remove logger, so that it won't show up in production
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "$componentName :-: $message")
        }
    }

}