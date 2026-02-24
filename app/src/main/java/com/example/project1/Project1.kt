package com.example.counterapp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(modifier: Modifier=Modifier) {
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Login Screen",
            fontSize = 40.sp)
        Spacer(Modifier.height(40.dp))
        TextField(value=username,
            onValueChange = {newValue ->
                username=newValue
            },
            label={Text("Enter Username")},
            modifier=Modifier.padding(8.dp))

        TextField(value=password,
            onValueChange = {newValue ->
                password=newValue
            },
            label={Text("Enter Password")},
            modifier=Modifier.padding(8.dp),
            visualTransformation = PasswordVisualTransformation())
        Button (onClick = {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
        },
            enabled = checkUsernameAndPassword(username,password)){
            Text("Click Me")
        }
    }
}

fun checkUsernameAndPassword(username: String, password: String): Boolean{
    return username.isNotBlank()&&password.isNotBlank()
}

@Composable
fun HomeScreen(modifier: Modifier=Modifier) {
    var search by remember{ mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier=modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(value=search,
            onValueChange = {newValue ->
                search=newValue
            },
            label={Text("Search Term")},
            modifier=Modifier.padding(8.dp))
        Button (onClick = {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
        },
            enabled = checkSearch(search)){
            Text("Search")
        }
    }

    Row(
        modifier=modifier.fillMaxSize()

    ) {
        Spacer(Modifier.width(50.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
            }
        ) {
            Text("Local News")
        }
        Spacer(Modifier.width(50.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show()
            }
        ) {
            Text("Top Headlines")
        }
        Spacer(Modifier.width(50.dp))
    }
}

fun checkSearch(search: String): Boolean{
    return search.isNotBlank()
}


@Preview(showBackground = true)
@Composable
fun LoginPreview(){
    LoginScreen()
}