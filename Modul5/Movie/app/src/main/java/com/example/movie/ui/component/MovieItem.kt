package com.example.movie.ui.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movie.domain.model.Movie
import java.net.URLEncoder

@Composable
fun MovieItem(
    movie: Movie,
    onDetailClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = "Poster film ${movie.title}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Rilis: ${movie.releaseDate}")
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(onClick = {
                        try {
                            val encodedTitle = URLEncoder.encode(movie.title, "UTF-8")
                            val url = "https://www.imdb.com/find?q=$encodedTitle"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }) {
                        Text("IMDb")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Button(onClick = {  onDetailClick(movie.id)  }) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}