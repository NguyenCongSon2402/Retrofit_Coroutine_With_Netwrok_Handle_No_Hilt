package com.example.retrofitcoroutinewithnetwrokhandle.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitcoroutinewithnetwrokhandle.model.DataResult
import com.example.retrofitcoroutinewithnetwrokhandle.network.ApiRepo
import com.example.retrofitcoroutinewithnetwrokhandle.network.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val apiRepo: ApiRepo) : ViewModel(){

    private val _stateListCharacter = MutableLiveData<Resource<DataResult>>()
    val stateCharacter: LiveData<Resource<DataResult>> = _stateListCharacter

    fun getAllCharacter(page: Int){
        viewModelScope.launch {
            _stateListCharacter.value = Resource.loading()
            apiRepo.getAllCharacter(page).collect(){
                if(it.status == Resource.Status.SUCCESS){
                    if(it.data != null){
                        _stateListCharacter.value = Resource.success(it.data) // data results
                    } else{
                        _stateListCharacter.value = Resource.failed(msg = "Data null")
                    }
                } else{
                    _stateListCharacter.value = Resource.failed(msg = "Call failed")
                }
            }
        }
    }
}