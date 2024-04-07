package com.example.androidmoviesapplicationtest.features.settings

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import com.example.androidmoviesapplicationtest.App
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsDataStore
@Inject
constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("ThemeSettings")
        private val THEME_SETTINGS = booleanPreferencesKey("theme_settings")

    }

    val isDark = mutableStateOf(false)
    private val scope = CoroutineScope(Main)


    init {
        observeDataStore()
    }

    private fun observeDataStore() {
        context.dataStore.data.onEach { preferences ->
            preferences[THEME_SETTINGS]?.let { isDarkTheme ->
                isDark.value = isDarkTheme
            }
        }.launchIn(scope)
    }

    fun toggleTheme() {
        scope.launch {
            context.dataStore.edit { preferences ->
                val current = preferences[THEME_SETTINGS] ?: false
                preferences[THEME_SETTINGS] = !current
            }
        }

    }
}