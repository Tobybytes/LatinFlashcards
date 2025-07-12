package com.example.latinflashcards

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

fun readVocabFromAssets(context: Context, filename: String = "vocabulary.json"): List<VocabEntry> {
    val jsonString = context.assets.open(filename).bufferedReader().use { it.readText() }
    return Json.decodeFromString(jsonString)
}
