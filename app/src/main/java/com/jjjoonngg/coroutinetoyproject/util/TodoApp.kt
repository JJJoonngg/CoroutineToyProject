package com.jjjoonngg.coroutinetoyproject.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class TodoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context

        fun getAppContext() = context
    }
}