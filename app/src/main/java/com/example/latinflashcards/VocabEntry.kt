// VocabEntry.kt
package com.yourapp.latinflashcards

data class VocabEntry(
    val chapter: Int,
    val speechPart: String,
    val latin: String,
    val english: String,
    val language: String = "Latin"
)