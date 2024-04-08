package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.componentes.MovieItem
import com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.componentes.SearchBarView


@Composable
fun TrendingMoviesScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {


    val viewModel = hiltViewModel<MovieListViewModel>()
    val searchText by viewModel.searchText.collectAsState()

   
        Column (
            modifier = Modifier.fillMaxSize(),
        ){
            SearchBarView(onEvent)
            if (movieListState.trendingMovieList.isEmpty()) {
                if (movieListState.isLoading){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                       CircularProgressIndicator()
                    }
                }else{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                         verticalArrangement = Arrangement.Center,
                         horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Inbox,
                            contentDescription = "Empty List",
                            modifier = Modifier.size(120.dp),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Empty List")
                    } 
                }
              
            }else {
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
                ) {

                    items(movieListState.trendingMovieList.size) { index ->
                        MovieItem(
                            movie = movieListState.trendingMovieList[index],
                            navHostController = navController
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (index >= movieListState.trendingMovieList.size - 1 && !movieListState.isLoading && searchText.isEmpty()) {
                            onEvent(MovieListUiEvent.Paginate)
                        }

                    }
                }
            }
        
        
    }

}






















