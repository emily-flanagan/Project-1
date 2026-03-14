package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                HomeScreen()
            }

        }
    }
}