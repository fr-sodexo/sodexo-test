package com.example.sodexo.model.api.manager

import com.example.sodexo.model.api.config.RetrofitConfig
import com.example.sodexo.model.schema.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import java.lang.Exception

object MovieApiManager {

    fun fetchMovies(): Observable<List<Movie>> {
        return Observable.create { emitter ->
            try {

                val request = RetrofitConfig.moviesApi().fetchMovies()
                val response = request.execute().body()?.string()
                val type = object : TypeToken<List<Movie>>() {}.type
                val movies = Gson().fromJson(response, type) as? List<Movie>?

                if (movies is List<*>) {
                    return@create emitter.onNext(movies)
                }

                return@create emitter.onNext(ArrayList())

            } catch (e: Exception) {
                return@create emitter.onNext(ArrayList())
            }
        }
    }

    fun fetchDetails(id: String?): Observable<Movie> {
        return Observable.create { emitter ->
            try {

                if (id == null) {
                    return@create emitter.onNext(Movie())
                }

                val request = RetrofitConfig.moviesApi().fetchDetails(id)
                val response = request.execute().body()?.string()
                val movie = Gson().fromJson(response, Movie::class.java)

                if (movie is Movie) {
                    return@create emitter.onNext(movie)
                }

                return@create emitter.onNext(Movie())

            } catch (e: Exception) {
                return@create emitter.onNext(Movie())
            }
        }
    }

}