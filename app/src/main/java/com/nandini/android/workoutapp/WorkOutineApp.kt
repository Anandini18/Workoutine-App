package com.nandini.android.workoutapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WorkOutineApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val db by lazy {

        HistoryDatabase.getInstance(this)
    }


}