package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project1.ui.theme.Project1Theme



// NEWSAPI cb46ed5a1484044b1955594ffa7eba2
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme() {
                // LoginScreen()
                //HomeScreen()
                // SourcesScreen()
                Navigate()
            }
        }
    }
// navigate function through the app
    @Composable
    fun Navigate() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate("home")
                    }
                )
            }
            composable("home") {
                HomeScreen(
                    onTopHeadlines = {
                        navController.navigate("topHeadlines")
                    },
                    onSearch = {
                        // https://www.geeksforgeeks.org/kotlin/jetpack-compose-navigation-and-passing-data-in-android/
                        navController.navigate("search/$it")
                    }
                )
            }
            composable("topHeadlines") {
                TopHeadlines()
            }
            // I was confused on how to pass data between screens using the navController so I used this link for help
            // https://www.geeksforgeeks.org/kotlin/jetpack-compose-navigation-and-passing-data-in-android/
            composable("search" + "/{searchTerm}") {navBackStack ->
                val term = navBackStack.arguments?.getString("searchTerm")
                SearchScreen(searchTerm = term)
            }
        }
    }
}