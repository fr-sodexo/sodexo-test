package com.example.sodexo.model.api.config

import com.example.sodexo.model.api.endpoint.MovieApi
import retrofit2.Retrofit

object RetrofitConfig {

    private var sRetrofitClient: Retrofit? = null

    private fun buildClient(): Retrofit {

        if (sRetrofitClient != null) {
            return sRetrofitClient as Retrofit
        }

        sRetrofitClient = Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL_TRAKT)
            .build()

        return sRetrofitClient as Retrofit

    }

    fun moviesApi(): MovieApi {
        return buildClient()
            .create(MovieApi::class.java)
    }

}
