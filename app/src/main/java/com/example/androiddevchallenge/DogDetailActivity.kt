package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.Dog
import dev.chrisbanes.accompanist.glide.GlideImage

class DogDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dog: Dog = intent.getSerializableExtra("dog") as Dog

        title = dog.name

        setContent {
            LazyColumn(Modifier.padding(10.dp)) {
                item {
                    GlideImage(
                        data = dog.image,
                        contentDescription = "",
                        modifier = Modifier.size(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Spacer(Modifier.height(20.dp))
                    Text(dog.desc, style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
}