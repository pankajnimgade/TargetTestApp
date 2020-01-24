package com.target.dealbrowserpoc.dealbrowser

import android.app.Application
import com.target.dealbrowserpoc.dealbrowser.di.AppContainer

/**
 * Created by Pankaj Nimgade on 1/21/2020.
 */
class TargetApplication : Application() {

    val appContainer = AppContainer(BuildConfig.API_BASE_URL, BuildConfig.DEBUG)

    override fun onCreate() {
        super.onCreate()
    }

}