package com.hacksy.tvmaze.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ApiClient {

    private val API_BASE_URL = "https://api.tvmaze.com/"

    private var servicesApiInterface: ServicesApiInterface?=null

    fun build(): ServicesApiInterface?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java)

        return servicesApiInterface as ServicesApiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    interface ServicesApiInterface{
        @GET("shows?page={page}")
        suspend fun listTvShows(@Path("page")page: Int): Response<List<TvShowsResponse>>
        @GET("search/shows?q={query}")
        suspend fun searchTvShows(@Path("query")show: String): Response<List<TvShowsResponse>>
    }
}