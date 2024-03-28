package com.example.retrofitcoroutinewithnetwrokhandle.network

import com.example.retrofitcoroutinewithnetwrokhandle.model.DataResult
import retrofit2.Response

class ApiDatasource(private val apiService: ApiService) {
    suspend fun getAllCharacter(page: Int): Response<DataResult>{
        return apiService.getListCharacter(page)
    }
}