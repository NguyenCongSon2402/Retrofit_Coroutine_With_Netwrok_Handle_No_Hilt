package com.example.retrofitcoroutinewithnetwrokhandle.network

import com.example.retrofitcoroutinewithnetwrokhandle.model.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import retrofit2.Response

class ApiRepo(private val apiDatasource: ApiDatasource) {
    suspend fun getAllCharacter(page:Int): Flow<Resource<DataResult>>{
        return flow {
            emit(safeApi {
                apiDatasource.getAllCharacter(page)
            })
        }.flowOn(
            Dispatchers.IO
        )
    }

    private suspend fun <T> safeApi(apiCall : suspend () -> Response<T>): Resource<T>{
        try {
            val response = apiCall()
            if(response.isSuccessful){
                if(response.body()!= null){
                    return Resource.success(response.body()!!)
                }
            } else{
                val code = response.code()
                if(code == 400){
                    return Resource.failed(msg = "Lỗi 400")
                }
                return Resource.failed(msg = "Có lỗi xảy ra")
            }
            return Resource.loading()
        }  catch (e: Exception){
            return Resource.failed(msg = "Có lỗi xảy ra, vui lòng thử lại")
        }
    }

}