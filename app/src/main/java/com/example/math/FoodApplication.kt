package com.example.math

import android.app.Application
import com.example.math.repo.Repository
import com.example.math.utils.FoodSharedPreference
import com.example.network.RetrofitClient

class FoodApplication: Application() {
    lateinit var repo: Repository
        private set

    lateinit var foodSharedPreference: FoodSharedPreference
        private set

    override fun onCreate() {
        super.onCreate()
        val retrofitInstance = RetrofitClient.getApiInstance()
        foodSharedPreference = FoodSharedPreference(this)
        repo = Repository(retrofitInstance, foodSharedPreference)
    }
}