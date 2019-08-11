package com.example.sodexo.controller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sodexo.model.api.manager.MovieApiManager
import com.example.sodexo.model.schema.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieRepository {

    private val mMovies = MutableLiveData<List<Movie>>()

    fun fetchMovies(): LiveData<List<Movie>> {
        MovieApiManager.fetchMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mMovies.value = it }
        return mMovies
    }

    fun fetchDetails(movie: Movie?): LiveData<Movie> {
        val movieDetails = MutableLiveData<Movie>()
        MovieApiManager.fetchDetails(movie?.ids?.trakt)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieDetails.value = it }
        return movieDetails
    }

}