package com.example.retrofitcoroutinewithnetwrokhandle.retrofit

import com.example.retrofitcoroutinewithnetwrokhandle.constant.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRetrofit {
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}