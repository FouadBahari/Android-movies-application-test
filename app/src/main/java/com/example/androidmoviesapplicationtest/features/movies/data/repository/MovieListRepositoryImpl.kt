package com.example.androidmoviesapplicationtest.features.movies.data.repository

import com.example.androidmoviesapplicationtest.features.movies.data.local.movie.MovieDatabase
import com.example.androidmoviesapplicationtest.features.movies.data.mappers.toMovie
import com.example.androidmoviesapplicationtest.features.movies.data.mappers.toMovieEntity
import com.example.androidmoviesapplicationtest.features.movies.data.remote.MovieApi
import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie
import com.example.androidmoviesapplicationtest.features.movies.domain.repository.MovieListRepository
import com.example.androidmoviesapplicationtest.core.util.Resource
import com.example.androidmoviesapplicationtest.features.movies.data.local.movie.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieList()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(
                    Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList(page)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(
                Resource.Success(
                movieEntities.map { it.toMovie() }
            ))
            emit(Resource.Loading(false))

        }
    }

    override suspend fun searchMovieList(
        forceFetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Movie>>>{
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.searchMovies(query)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(
                    Resource.Success(
                        data = localMovieList.map { movieEntity ->
                            movieEntity.toMovie()
                        }
                    ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.searchMoviesList(query)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }

            movieDatabase.movieDao.upsertMovieList(movieEntities)

            emit(
                Resource.Success(
                    movieEntities.map { it.toMovie() }
                ))
            emit(Resource.Loading(false))

        }
    }
    override suspend fun getMovie(
        id: Int): Flow<Resource<Movie>> {
        return flow {

            emit(Resource.Loading(true))

            val movieEntity: MovieEntity = movieDatabase.movieDao.getMovieById(id)


            if (movieEntity != null) {
                emit(
                    Resource.Success(movieEntity.toMovie())
                )

                emit(Resource.Loading(false))
                return@flow
            } else {
                val movieDetailsFromApi = try {
                    movieApi.getMoviesDetails(id)
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading movies"))
                    return@flow
                } catch (e: HttpException) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading movies"))
                    return@flow
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error(message = "Error loading movies"))
                    return@flow
                }
                val movieEntityFromApi = movieDetailsFromApi.toMovieEntity()

                emit(
                    Resource.Success(movieEntityFromApi.toMovie())
                )
            }

            emit(Resource.Error("Error no such movie"))

            emit(Resource.Loading(false))


        }
    }
}



















