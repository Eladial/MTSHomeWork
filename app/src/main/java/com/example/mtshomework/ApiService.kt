package com.example.mtshomework

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://api.themoviedb.org/3/"
const val API_KEY = "471cbcd6c7ff7a3c182557ddcfae63b6"
const val LANGUAGE = "ru"
const val PAGE = "1"

interface ApiService {
    @GET("movie/popular")
    fun getPopular(
        @Query("api_key")
        api_key: String = API_KEY,

        @Query("language")
        language: String = LANGUAGE,

        @Query("page")
        page: String = PAGE
    ): Call<PopularResponse>


    companion object{
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        private fun OkHttpClient.Builder.addHeaderInterceptor() = apply {
            val interceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("api_key", API_KEY)
                    .build()

                chain.proceed(request)
            }

            this.addInterceptor(interceptor)
        }

        private fun OkHttpClient.Builder.addHttpLoggingInterceptor() = apply {
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                this.addNetworkInterceptor(interceptor)
            }
        }

        fun Retrofit.Builder.setClient() = apply {
            val okHttpClient = OkHttpClient.Builder()
                .addHeaderInterceptor()
                .addHttpLoggingInterceptor()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

            this.client(okHttpClient)
        }
    }

}