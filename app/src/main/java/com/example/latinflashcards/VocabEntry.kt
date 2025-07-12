package com.example.latinflashcards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VocabEntry(
    @SerialName("Chapter") val chapter: Double,
    @SerialName("Part of Speech") val speechPart: String,
    @SerialName("Lang") val latin: String,
    @SerialName("English") val english: String
)