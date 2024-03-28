package com.example.retrofitcoroutinewithnetwrokhandle.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitcoroutinewithnetwrokhandle.network.ApiRepo

class HomeViewModelFactory(private val apiRepo: ApiRepo):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepo) as T
    }
}