package com.example.sodexo.model.schema

import java.io.Serializable

data class Movie(
    var ids: MovieIds? = null,
    var title: String? = null,
    var year: String? = null,
    var overview: String? = null,
    var rating: Float? = null
): Serializable {

    data class MovieIds(
        val trakt: String?,
        val imdb: String?
    ): Serializable

}