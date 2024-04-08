@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.componentes


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.androidmoviesapplicationtest.features.movies.presentaion.movieDetails.DetailsViewModel
import com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.MovieListState
import com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.MovieListUiEvent
import com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.MovieListViewModel

@Composable
fun SearchBarView(
    onEvent: (MovieListUiEvent) -> Unit
) {

    val viewModel = hiltViewModel<MovieListViewModel>()
    val searchText by viewModel.searchText.collectAsState()


    TextField(
        value = searchText,
        onValueChange =  { value ->
            viewModel.onSearchTextChange(value)
            onEvent(MovieListUiEvent.Search(value))
        },
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            ),
        textStyle = TextStyle(fontSize = 18.sp),
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        singleLine = true,
        shape = RectangleShape,
        )
}
