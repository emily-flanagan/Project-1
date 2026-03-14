package com.example.project1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

    @Composable
    fun SourcesScreen(modifier: Modifier =Modifier){
        val sourcesList=getFakeData()
        LazyColumn(modifier=modifier){
            items(sourcesList) {currentSource ->
                SourceCard(
                    source=currentSource,
                    modifier=Modifier.padding(1.dp)
                )
            }
        }
    }
    @Composable
    fun SourceCard(source: Sources, modifier:Modifier=Modifier){
        Card(modifier=Modifier.fillMaxWidth()
            .padding(1.dp)){
            Row(modifier=Modifier.padding(5.dp)) {
                Spacer(modifier=Modifier.width(5.dp))
                Column() {
                    Text(source.SourceName)
                }

            }
        }
    }
    data class Sources(
        val SourceName: String,
    )
    fun getFakeData():List<Sources>{
        return listOf(
            Sources("The New York Times"),
            Sources("Associated Press"),
            Sources("Reuters"),
            Sources("NBC News"),
            Sources("ESPN"),
            Sources("CBS News"),
            Sources("CNN"),
            Sources("ABC News"),
            Sources("BBC"),
            Sources("The Wall Street Journal"),
            Sources("USA Today"),
            Sources("The Washington Post"),
            Sources("Politico"),
            Sources("National Public Radio"),
            Sources("The New Yorker"),
            Sources("Time Magazine"),
            Sources("US News & World Report"),
            Sources("The Andover Townsman"),
            Sources("The Eagle Tribune"),
            Sources("The Boston Globe"),
            Sources("Al Jazeera"),
            Sources("Agence France-Presse"),
            Sources("Le Monde"),
            Sources("The Guardian"),
            Sources("The GW Hatchet")

        )
    }

    @Preview(showBackground = true)
    @Composable
    fun SourcePreview(){
        SourcesScreen()
    }
