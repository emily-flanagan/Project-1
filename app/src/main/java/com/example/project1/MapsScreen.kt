package com.example.project1

import android.location.Geocoder
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale

@Composable
fun DisplayMap(onSearch: (String)-> Unit,modifier: Modifier =Modifier) {
    val singapore= LatLng(1.35,103.87)
    val markerState = remember { MarkerState(singapore) }
    var addressInfo by remember {mutableStateOf("")}
    val cameraPositionState = rememberCameraPositionState {
        CameraPosition.fromLatLngZoom(singapore,10f)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = {
                markerState.position = it
                addressInfo = "Resolving Address..."
                println("Long clicked at $(it.latitude), $(it.longitude")

            }
        ) {
            Marker(
                state = markerState,
                title = addressInfo,
                snippet = "${markerState.position.latitude}" + "${markerState.position.longitude}"
            )
        }
        Button(
            onClick = {
                val latitude = markerState.position.latitude
                val longitude = markerState.position.longitude

                onSearch("$latitude , $longitude")
            },
            modifier= Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        ) {
            Text("Location")
        }
    }

}

