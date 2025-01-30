package com.example.nutrak.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

suspend fun <T> DataStore<Preferences>.read(key: Preferences.Key<T>): T? {
    return data.map { it[key] }.first()
}