package com.example.androidmoviesapplicationtest.features.trendingMovies.data.remote

import com.example.androidmoviesapplicationtest.features.trendingMovies.data.remote.respnod.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    }

}