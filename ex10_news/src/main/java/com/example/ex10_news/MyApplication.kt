package com.example.ex10_news

import android.app.Application
import com.example.ex10_news.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 레트로핏 초기화(어플이 구동될때 한번만 실행될 설정)
class MyApplication: Application() {
    companion object {
        val API_KEY = "e26a78762c2d4669a83047727f043c34" // 회원가입을 통해 얻은 key값
        val BASE_URL = "https://newsapi.org" // 무료 뉴스api 제공 사이트
        var networdService: NetworkService
        val retrofit: Retrofit // 레트로핏 최초 설정
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //데이터 컨버팅을위한 세팅
                .build()
        init {
            networdService = retrofit.create(NetworkService::class.java)//인터페이스에 네트워킹이 가능하도록 생성
        }
    }
}