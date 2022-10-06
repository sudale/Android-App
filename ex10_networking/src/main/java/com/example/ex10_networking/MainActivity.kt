package com.example.ex10_networking

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.ex10_networking.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getReq.setOnClickListener {
            var userListCall = MyApplication.networkService.doGetUserList("1")
            userListCall.enqueue(object: Callback<UserListModel>{
                //통신이 성공함
                override fun onResponse(
                    call: Call<UserListModel>,
                    response: Response<UserListModel>
                ) {
                    if(response.isSuccessful){
                        //    Log.d("myLog","네트워킹 성공 : ${response.raw()}")
                        var userList = response.body()
                        Log.d("myLog","userList : ${userList}")
                    }
                }
                //통신이 실패함
                override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                    Log.d("myLog","네트워킹 실패")
                }

            })
        }

        binding.getPath.setOnClickListener {
            val userModel = MyApplication.networkService.test2(1)
            userModel.enqueue(object :Callback<ResponseData>{
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    Log.d("myLog","getPath 네트워킹 성공 : ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                    Log.d("myLog","getPath 네트워킹 실패")
                }
            })
        }

        binding.getQueryMap.setOnClickListener {
            val userModel = MyApplication.networkService.test3(
                mapOf("one" to "안드로이드", "two" to "스튜디오"),
                "sik"
            )
            userModel.enqueue(object :Callback<UserModel>{
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    Log.d("myLog","get QueryMap 네트워킹 성공 : ${response.body()}")
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d("myLog","get QueryMap 네트워킹 실패")
                }
            })
        }

        binding.glideTest.setOnClickListener {
            Glide.with(this)
                .load("https://reqres.in/img/faces/1mage.jpg")
                .override(500,500) // 크기 조절
                .placeholder(R.mipmap.ic_launcher_round) // 이미지 로딩을 시작하기전에 보여줄 이미지
                .error(R.drawable.todo) // 리소스를 불러오다가 에러가 발생했을때 보여줄 이미지
                .into(binding.glideImage)
        }
    }
}

class  MyApplication:Application(){
    companion object{
        var retrofit: Retrofit
        lateinit var networkService : INetworkService
        init {
            retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            networkService = retrofit.create(INetworkService::class.java)
        }
    }
}