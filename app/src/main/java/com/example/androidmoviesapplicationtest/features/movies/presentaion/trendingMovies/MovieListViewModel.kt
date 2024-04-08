package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmoviesapplicationtest.features.movies.domain.repository.MovieListRepository
import com.example.androidmoviesapplicationtest.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        getTrendingMovieList(false)
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            is MovieListUiEvent.Paginate -> {
                    getTrendingMovieList(true)
            }

            is MovieListUiEvent.Search -> {
                getTrendingMovieList(false,event.query)
            }

            MovieListUiEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentTrendingScreen = !movieListState.value.isCurrentTrendingScreen
                    )
                }
            }
        }
    }

    fun onSearchTextChange(query: String) {
        _searchText.value = query
    }
    private fun getTrendingMovieList(forceFetchFromRemote: Boolean,query: String? = "") {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            if (query != null && query.isEmpty()) {
                movieListRepository.getMovieList(
                    forceFetchFromRemote,
                    movieListState.value.trendingMovieListPage
                ).collectLatest { result ->
                    when (result) {
                        is Resource.Error -> {
                            _movieListState.update {
                                it.copy(isLoading = false)
                            }
                        }

                        is Resource.Success -> {
                            result.data?.let { trendingList ->
                                _movieListState.update {
                                    it.copy(
                                        trendingMovieList = movieListState.value.trendingMovieList
                                                + trendingList.shuffled(),
                                        trendingMovieListPage = movieListState.value.trendingMovieListPage + 1
                                    )
                                }
                            }
                        }

                        is Resource.Loading -> {
                            _movieListState.update {
                                it.copy(isLoading = result.isLoading)
                            }
                        }
                    }
                }
            }else{
                if (query != null) {
                    movieListRepository.searchMovieList(
                        forceFetchFromRemote,
                        query
                    ).collectLatest { result ->
                        when (result) {
                            is Resource.Error -> {
                                _movieListState.update {
                                    it.copy(isLoading = false)
                                }
                            }

                            is Resource.Success -> {
                                result.data?.let { trendingList ->
                                    _movieListState.update {
                                        it.copy(
                                            trendingMovieList = trendingList,
                                        )
                                    }
                                }
                            }

                            is Resource.Loading -> {
                                _movieListState.update {
                                    it.copy(isLoading = result.isLoading)
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}


























