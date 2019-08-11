package com.example.sodexo.model.api.endpoint

import com.example.sodexo.model.api.config.ApiConfig
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MovieApi {

    @GET(ApiConfig.ENDPOINT_MOVIE_LIST)
    fun fetchMovies(
        @Header("trakt-api-key") traktApiKey: String = ApiConfig.KEY_TRAKT
    ): Call<ResponseBody>

    @GET(ApiConfig.ENDPOINT_MOVIE_DETAILS)
    fun fetchDetails(
        @Path("id") id: String,
        @Header("trakt-api-key") traktApiKey: String = ApiConfig.KEY_TRAKT
    ): Call<ResponseBody>

}