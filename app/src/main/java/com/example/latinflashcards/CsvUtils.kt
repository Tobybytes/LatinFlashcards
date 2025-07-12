// CsvUtils.kt
package com.yourapp.latinflashcards

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun readCsvFromAssets(context: Context): List<VocabEntry> {
    val inputStream = context.assets.open("vocabulary.csv")
    val reader = BufferedReader(InputStreamReader(inputStream))
    val entries = mutableListOf<VocabEntry>()

    reader.readLine() // skip header
    reader.forEachLine { line ->
        val tokens = line.split(",")
        if (tokens.size >= 5) {
            val entry = VocabEntry(
                chapter = tokens[0].toIntOrNull() ?: 0,
                speechPart = tokens[1].trim(),
                latin = tokens[2].trim(),
                english = tokens[3].trim(),
                language = tokens[4].trim()
            )
            entries.add(entry)
        }
    }
    return entries
}
