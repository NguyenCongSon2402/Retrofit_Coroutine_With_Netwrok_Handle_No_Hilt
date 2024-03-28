package com.example.retrofitcoroutinewithnetwrokhandle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitcoroutinewithnetwrokhandle.adapter.CharacterAdapter
import com.example.retrofitcoroutinewithnetwrokhandle.databinding.ActivityMainBinding
import com.example.retrofitcoroutinewithnetwrokhandle.model.Character
import com.example.retrofitcoroutinewithnetwrokhandle.network.ApiDatasource
import com.example.retrofitcoroutinewithnetwrokhandle.network.ApiRepo
import com.example.retrofitcoroutinewithnetwrokhandle.network.ApiService
import com.example.retrofitcoroutinewithnetwrokhandle.network.Resource
import com.example.retrofitcoroutinewithnetwrokhandle.retrofit.GetRetrofit
import com.example.retrofitcoroutinewithnetwrokhandle.view_model.HomeViewModel
import com.example.retrofitcoroutinewithnetwrokhandle.view_model.HomeViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter : CharacterAdapter
    private var mListCharacter : ArrayList<Character> = arrayListOf()

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = GetRetrofit().getInstance().create(ApiService::class.java)
        val apiDatasource = ApiDatasource(apiService)
        val apiRepo = ApiRepo(apiDatasource)
        val factory = HomeViewModelFactory(apiRepo)

        homeViewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        homeViewModel.getAllCharacter(1)

        initView()
        initObserve()
    }

    private fun initView(){
        mAdapter = CharacterAdapter(this, mListCharacter)
        val linearLayout = LinearLayoutManager(this)
        binding.rcv.adapter = mAdapter
        binding.rcv.layoutManager = linearLayout
    }

    private fun initObserve(){
        homeViewModel.stateCharacter.observe(this){
            when(it.status){
                Resource.Status.LOADING->{
                    Toast.makeText(this@MainActivity, "Loading ... ", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.SUCCESS ->{
                    val list = it.data?.results as ArrayList<Character>
                    mListCharacter.addAll(list)
                    mAdapter.notifyDataSetChanged()
                }
                Resource.Status.FAILED -> {
                    val msg = it.message
                    Toast.makeText(this@MainActivity, "$msg", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}