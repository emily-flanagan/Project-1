package com.example.project1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


    @Composable
    fun HomeScreen(onTopHeadlines: ()-> Unit,onSearch: (String)-> Unit,onMap: ()-> Unit, modifier: Modifier=Modifier ) {
        var search by remember{ mutableStateOf("") }
        val context = LocalContext.current
        Column(
            modifier=modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            // search field for user to enter a value to pass to the headline manager
            TextField(value=search,
                onValueChange = {newValue ->
                    search=newValue
                },
                label={Text("Search Term")},
                modifier=Modifier.padding(8.dp))
            Button (onClick = { onSearch(search)
                //Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
            },
                enabled = checkSearch(search)){
                Text("Search")
            }
            //Spacer(Modifier.width(50.dp))
            // Go to the map screen to see local news based on a location the user selects
            Button(
                onClick = { onMap()
                    //Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Local News")
            }
            //Spacer(Modifier.width(50.dp))
            // Go to the top headlines screen to see all top headlines (default general, user can choose from dropdown)
            Button(
                onClick = { onTopHeadlines()
                    //Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Top Headlines")
            }
            //Spacer(Modifier.width(50.dp))
        }
    }

fun checkSearch(search: String): Boolean{
        return search.isNotBlank()
    }

    /*@Preview(showBackground = true)
    @Composable
    fun HomePreview(){
        HomeScreen()
    }*/
