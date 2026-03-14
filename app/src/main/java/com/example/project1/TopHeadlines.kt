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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Spinner idea in Composable https://developer.android.com/develop/ui/compose/components/menu
@Composable
fun DropdownMenu() {
    var list = listOf("General", "Business", "Entertainment", "Health", "Science", "Sports")
    var isExpanded by remember {mutableStateOf(false)}
    Column(
        modifier=Modifier
            .fillMaxWidth()
            .padding(horizontal=8.dp)
    )


    var expanded by remember { mutableStateOf(false) }
    var category by remember {mutableStateOf("general")}
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Business") },
                onClick = { category = "business"}
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
}

@Composable
fun TopHeadlines(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val apiKey = context.getString(R.string.HeadlineKey)
    val headlineManager = HeadlineManager()
    var myHeadlineList by remember { mutableStateOf<List<HeadlineData>>(emptyList()) }
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            headlineManager.retrieveHeadlines("general", apiKey)
        }
        myHeadlineList = result
        Log.d("HeadlineCount", "myHeadlineList is ${myHeadlineList.size}")
    }
    LazyColumn(modifier = modifier) {
        items(myHeadlineList.size) { currentHeadline ->
            HeadlineCard(
                headline = myHeadlineList.get(currentHeadline),
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}

@Composable
fun categoryHeadline(modifier: Modifier = Modifier) {

}

    @Composable
    fun HeadlineCard(headline: HeadlineData, modifier: Modifier = Modifier) {
        val context = LocalContext.current
        Card(
            modifier = Modifier.fillMaxWidth()
            .padding(1.dp)
            //add
            .clickable(
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
                    Text(headline.title)
                    Text(headline.source)
                    Text(headline.description)
                    //Text(yelp.url)
                }

            }
        }
    }
