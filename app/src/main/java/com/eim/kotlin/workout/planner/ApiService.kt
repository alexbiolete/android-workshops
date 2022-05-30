package com.eim.kotlin.workout.planner

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("everything?q=workout&sortBy=publishedAt&apiKey=68b2a840425b46ada1bf2426f5a229ea")
    fun getData(): Call<DataModel>
}
