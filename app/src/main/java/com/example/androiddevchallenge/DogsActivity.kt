/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.Dog
import com.example.androiddevchallenge.ui.DogsPresenter
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.glide.GlideImage
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mPresenter: DogsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ImageList(mPresenter.getDogs(), this)
        }
    }
}

@Composable
fun ImageList(dogs: List<Dog>, context: Context) {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(dogs.size) {
            ImageListItem(dogs[it], context)
        }
    }
}

@Composable
fun ImageListItem(dog: Dog, context: Context) {
    Column(Modifier.padding(10.dp)) {
        Row {
            GlideImage(
                data = dog.image,
                contentDescription = "",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(20.dp))
            Column {
                Text(
                    text = dog.desc,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 4,
                    style = MaterialTheme.typography.subtitle1
                )
                Spacer(Modifier.height(10.dp))
                Button(onClick = {
                    val intent = Intent(context, DogDetailActivity::class.java)
                    intent.putExtra("dog", dog)
                    context.startActivity(intent)
                }) {
                    Text(text = "了解更多")
                }
            }
        }
    }
}