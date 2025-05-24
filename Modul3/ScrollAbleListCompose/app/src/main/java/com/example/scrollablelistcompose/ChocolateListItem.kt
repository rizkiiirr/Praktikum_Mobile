package com.example.scrollablelistcompose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.scrollablelistcompose.data.Chocolate

@Composable
fun ChocolateListItem(chocolate: Chocolate, onDetailClick: (Chocolate) -> Unit) {
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

            Image(
                painter = painterResource(id = chocolate.chocolateImageId),
                contentDescription = null,
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
                Text(text = "${chocolate.title}")
                Text(text = "No: ${chocolate.no}")



                Text(
                    text = "${chocolate.description}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(chocolate.btnLink))
                        context.startActivity(intent)
                    }) {
                        Text("IMDB")
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Button(onClick = {
                        onDetailClick(chocolate)

                    }) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}