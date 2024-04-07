package com.example.androidmoviesapplicationtest.features.settings

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) : ViewModel() {


    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    init {
        viewModelScope.launch {
            _isDarkTheme.value =  sharedPreferences.getBoolean(KEY_THEME,false)

        }

    }

    fun toggleTheme() = viewModelScope.launch {
        _isDarkTheme.value = !_isDarkTheme.value
        sharedPreferences.edit().putBoolean(KEY_THEME, _isDarkTheme.value).apply()

    }

    companion object {
        const val KEY_THEME = "theme"
    }
}