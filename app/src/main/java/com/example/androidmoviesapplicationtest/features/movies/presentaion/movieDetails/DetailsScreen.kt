package com.example.androidmoviesapplicationtest.features.movies.presentaion.movieDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.androidmoviesapplicationtest.R
import com.example.androidmoviesapplicationtest.features.movies.data.remote.MovieApi
import com.example.androidmoviesapplicationtest.core.util.RatingBar



@Composable
fun DetailsScreen(
    navController: NavHostController
) {

    val detailsViewModel = hiltViewModel<DetailsViewModel>()
    val detailsState = detailsViewModel.detailsState.collectAsState().value

    val backDropImageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + detailsState.movie?.backdrop_path)
            .size(Size.ORIGINAL)
            .build()
    ).state



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (backDropImageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = detailsState.movie?.title
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 12.dp),
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        if (backDropImageState is AsyncImagePainter.State.Success) {

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    painter = backDropImageState.painter,
                    contentDescription = detailsState.movie?.title,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 12.dp),
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        detailsState.movie?.let { movie ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = movie.title,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    RatingBar(
                        starsModifier = Modifier.size(18.dp),
                        rating = movie.vote_average / 2,
                        starsColor = Color.Yellow
                    )

                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = movie.vote_average.toString().take(3),
                        fontSize = 14.sp,
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = "(${ movie.vote_count.toString() + stringResource(R.string.votes)})"
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.language) + movie.original_language
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.release_date) + movie.release_date
                )
            }
        }


        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(R.string.description),
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        detailsState.movie?.let {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = it.overview,
                fontSize = 16.sp,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))


    }

}


















