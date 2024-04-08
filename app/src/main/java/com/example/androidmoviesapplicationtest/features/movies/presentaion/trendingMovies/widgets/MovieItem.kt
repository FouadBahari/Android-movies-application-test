package com.example.androidmoviesapplicationtest.features.movies.presentaion.trendingMovies.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.androidmoviesapplicationtest.features.movies.data.remote.MovieApi
import com.example.androidmoviesapplicationtest.features.movies.domain.model.Movie
import com.example.androidmoviesapplicationtest.core.util.RatingBar
import com.example.androidmoviesapplicationtest.core.util.Screen
import java.text.DecimalFormat
import java.text.SimpleDateFormat


@Composable
fun MovieItem(
    movie: Movie,
    navHostController: NavHostController
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + movie.poster_path)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    val dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onBackground,
                        dominantColor
                    )
                )
            )
            .clickable {
                navHostController.navigate(Screen.Details.rout + "/${movie.id}")
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            if (imageState is AsyncImagePainter.State.Error) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = "Image not available",
                    tint = MaterialTheme.colorScheme.surface,
                )
            } else {
                imageState.painter?.let {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = it,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                    .background(Color.Red)
                    ,
                contentAlignment = Alignment.Center
            ) {
                parseDateToYear(movie.release_date)?.let {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        text = it,
                        color = Color.White,
                        fontSize = 12.sp


                    )
                }
            }

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        text = movie.title,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Rating
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RatingBar(
                            modifier = Modifier.size(18.dp),
                            rating = movie.vote_average / 2
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = DecimalFormat("#.#").format(movie.vote_average),
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 12.sp,
                            maxLines = 1
                        )
                    }
                }

            }

        }

    }


}

@SuppressLint("SimpleDateFormat")
fun parseDateToYear(dateStr: String): String? {
    return try {
        val date = SimpleDateFormat("yyyy").parse(dateStr)
        SimpleDateFormat("yyyy").format(date)

    } catch (e: Exception) {
        println("Invalid date format: $dateStr")
        null
    }
}














