package com.example.project1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

// Spinner idea in Composable https://developer.android.com/develop/ui/compose/components/menu
@Composable
fun MinimalDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
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
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Entertainment") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("General") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Health") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Science") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Sports") },
                onClick = { /* Do something... */ }
            )
            DropdownMenuItem(
                text = { Text("Technology") },
                onClick = { /* Do something... */ }
            )
        }
    }
}
