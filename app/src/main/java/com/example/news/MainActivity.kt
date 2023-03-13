@file:Suppress("DEPRECATION")

package com.example.news

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.news.response.Article
import com.example.news.ui.theme.NewsTheme
import com.example.news.viewModel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                News()
            }
        }
    }
}

@Composable
fun News() {
    val viewModel :NewsViewModel = viewModel()
    val rememberedNews = remember {
        mutableStateOf(emptyList<Article>())
    }
    viewModel.getNews {
        val newsFromApi = it!!.articles
        rememberedNews.value = newsFromApi
    }

    News2(rememberedNews)
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun News2(rememberedNews: MutableState<List<Article>>) {
    val context = LocalContext.current
    LazyColumn {
        items(rememberedNews.value) {

            Column(modifier = Modifier.padding(5.dp).clickable {
                openTab(context, it.url)
            }
            ) {
                GlideImage(model = it.urlToImage, contentDescription = null, contentScale = ContentScale.FillWidth)
                Text(text = it.title)
                Divider(thickness = 1.dp,color =MaterialTheme.colors.onSecondary)
            }

        }
    }
}

fun openTab(context: Context, urlNews: String) {
    val packageName =  "com.android.chrome"
    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    builder.setInstantAppsEnabled(true)
    builder.setToolbarColor(ContextCompat.getColor(context, R.color.orange))
    val customBuilder = builder.build()

    customBuilder.intent.setPackage(packageName)
    customBuilder.launchUrl(context, Uri.parse(urlNews))
}


