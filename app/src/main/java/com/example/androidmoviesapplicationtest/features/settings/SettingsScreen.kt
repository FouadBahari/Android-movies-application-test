package com.example.androidmoviesapplicationtest.features.settings

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SettingsScreen  (settingsDataStore: SettingsDataStore) {



    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Theme Settings", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.width(16.dp))
        Switch(
            checked = settingsDataStore.isDark.value,

                    onCheckedChange = {
                settingsDataStore.toggleTheme()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.secondary,
                checkedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer

            )
        )
        Text(
            text = if (settingsDataStore.isDark.value) "Dark Theme" else "Light Theme",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}