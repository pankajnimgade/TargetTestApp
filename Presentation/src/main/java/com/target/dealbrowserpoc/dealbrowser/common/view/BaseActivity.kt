package com.target.dealbrowserpoc.dealbrowser.common.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.target.dealbrowserpoc.dealbrowser.BuildConfig

/**
 * Created by Pankaj Nimgade on 1/21/2020.
 */
abstract class BaseActivity : AppCompatActivity() {


    companion object {
        // this shall help distinguish logs between Fragment vs Activity
        private val TAG = BaseActivity::class.java.simpleName
    }

    protected var isTablet: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeAppComponent()
    }

    private fun initializeAppComponent() {


    }

    /**
     * This is should be used for [AppCompatActivity] level logging
     */
    protected fun logMessage(componentName: String, message: String) {
        // A friendly check in case forgotten to remove logger, so that it won't show up in production
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "$componentName :-: $message")
        }
    }

    private fun isTablet(context: Context?) {
        context?.let {
            //            isTablet = it.resources.getBoolean(R.bool.isTablet)
        }
    }

}