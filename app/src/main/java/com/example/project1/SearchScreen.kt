package com.example.project1

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun SearchScreen(searchTerm: String?, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val apiKey = context.getString(R.string.HeadlineKey)
    val headlineManager = HeadlineManager()
    var myHeadlineList by remember { mutableStateOf<List<HeadlineData>>(emptyList()) }
    var category by remember {mutableStateOf("general")}

    // need to pass searchTerm from home screen to here
    LaunchedEffect(searchTerm) {
        val result = withContext(Dispatchers.IO) {
            headlineManager.retrieveSearch(searchTerm, apiKey)
        }
        myHeadlineList = result
        Log.d("HeadlineCount", "myHeadlineList is ${myHeadlineList.size}")
    }
    var expanded by remember { mutableStateOf(false) }
    Column() {
        // Spinner idea in Composable https://developer.android.com/develop/ui/compose/components/menu
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Default.Menu, contentDescription = "More options")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Business") },
                    onClick = { category = "business" }
                )
                DropdownMenuItem(
                    text = { Text("Entertainment") },
                    onClick = { category = "entertainment" }
                )
                DropdownMenuItem(
                    text = { Text("General") },
                    onClick = { category = "general" }
                )
                DropdownMenuItem(
                    text = { Text("Health") },
                    onClick = { category = "health" }
                )
                DropdownMenuItem(
                    text = { Text("Science") },
                    onClick = { category = "science" }
                )
                DropdownMenuItem(
                    text = { Text("Sports") },
                    onClick = { category = "sports" }
                )
                DropdownMenuItem(
                    text = { Text("Technology") },
                    onClick = { category = "technology" }
                )
            }
        }
        LazyColumn(modifier = modifier) {
            // actual call to the headline card
            items(myHeadlineList.size) { currentHeadline ->
                SearchCard(
                    headline = myHeadlineList.get(currentHeadline),
                    modifier = Modifier.padding(1.dp)
                )
            }
        }
    }
}

// creates the card for the top headlines based on what the user selected from the dropdown
@Composable
fun SearchCard(headline: HeadlineData, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(1.dp)
            //add
            .clickable(
                // when the user clicks on an article it should take them to the article on the internet
                onClick = {
                    val headlineCardIntent = Intent(Intent.ACTION_VIEW)
                        .apply {
                            data = headline.url.toUri()
                        }
                    context.startActivity(headlineCardIntent)
                }
            )

    ) {

        Row(modifier = Modifier.padding(2.dp)) {
            // Image(
            // painter = painterResource(R.drawable.adventure_brewing),
            // image thumbnail from the article
            AsyncImage(
                model = headline.image,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(1.dp)
            )
            //AsyncImage  use model verses painter. model=yelp.icon
            Spacer(modifier = Modifier.width(5.dp))
            Column() {
                // display the title, source, and description of the article
                Text(headline.title)
                Text(headline.source)
                Text(headline.description)
                //Text(yelp.url)
            }

        }
    }
}


