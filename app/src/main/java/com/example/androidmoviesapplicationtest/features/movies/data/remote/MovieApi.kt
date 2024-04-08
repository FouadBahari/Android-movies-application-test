package com.example.androidmoviesapplicationtest.features.movies.data.remote

import com.example.androidmoviesapplicationtest.features.movies.data.remote.respnod.MovieDto
import com.example.androidmoviesapplicationtest.features.movies.data.remote.respnod.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("search/movie")
    suspend fun searchMoviesList(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("movie/{id}")
    suspend fun getMoviesDetails(
        @Path(value = "id", encoded = false) id: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    }

}