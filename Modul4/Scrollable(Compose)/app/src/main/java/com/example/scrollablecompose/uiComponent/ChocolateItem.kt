package com.example.scrollablecompose.uiComponent

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scrollablecompose.R
import com.example.scrollablecompose.data.Chocolate
import timber.log.Timber

@Composable
fun ChocolateItem(
    chocolate: Chocolate,
    onDetailClicked: (Chocolate) -> Unit
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
                Text(text = chocolate.title)
                Text(text = "No: ${chocolate.no}")
                Text(
                    text = chocolate.description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Button(onClick = {
                        Timber.d("Link button clicked: ${chocolate.btnLink}")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(chocolate.btnLink))
                        context.startActivity(intent)
                    }) {
                        Text("IMDB")
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Button(onClick = {
                        Timber.d("Detail button clicked: ${chocolate.title}")
                        onDetailClicked(chocolate)

                    }) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ChocolateItemPreview(){
    val chocolate = Chocolate(
        no = 1,
        title = "Dark Chocolate",
        description = "Dark chocolate is a form of chocolate made from cocoa solids, cocoa butter and sugar. " +
                "It has a higher cocoa percentage than white chocolate, milk chocolate, and semisweet chocolate.",
        chocolateImageId = R.drawable.darkchocolate,
        btnLink = "https://en.wikipedia.org/wiki/Dark_chocolate"
    )
    ChocolateItem(
        chocolate= chocolate,
        onDetailClicked= { }

    )
}