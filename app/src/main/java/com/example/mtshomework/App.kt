package com.example.mtshomework

import android.app.Application


class App: Application() {
    init {
        instance = this
    }

    val apiService: ApiService by lazy { ApiService.create() }



    companion object {
        lateinit var instance: App
        private set
    }


}
