package com.example.latinflashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.latinflashcards.ui.theme.LatinFlashcardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vocabList = readVocabFromAssets(this)

        setContent {
            LatinFlashcardsTheme {
                FlashcardApp(vocabList)
            }
        }
    }
}
