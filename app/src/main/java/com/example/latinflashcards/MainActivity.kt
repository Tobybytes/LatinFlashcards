// MainActivity.kt
package com.yourapp.latinflashcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourapp.latinflashcards.ui.theme.LatinFlashcardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vocabList = readCsvFromAssets(this)

        setContent {
            LatinFlashcardsTheme {
                FlashcardApp(vocabList)
            }
        }
    }
}

@Composable
fun FlashcardApp(vocabList: List<VocabEntry>) {
    var selectedMode by remember { mutableStateOf("Trial") }
    var currentIndex by remember { mutableStateOf(0) }

    val currentEntry = vocabList.getOrNull(currentIndex)

    Column(modifier = Modifier.padding(16.dp)) {
        // Mode Toggle
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { selectedMode = "Trial" }) { Text("Trial Mode") }
            Button(onClick = { selectedMode = "Learning" }) { Text("Just Learning") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        currentEntry?.let { entry ->
            Text("Latin: ${entry.latin}", style = MaterialTheme.typography.headlineMedium)
            if (selectedMode == "Learning") {
                Spacer(modifier = Modifier.height(8.dp))
                Text("English: ${entry.english}", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (selectedMode == "Trial") {
                TrialModeButtons(onNext = { currentIndex = (currentIndex + 1) % vocabList.size })
            } else {
                LearningModeButtons(onNext = { currentIndex = (currentIndex + 1) % vocabList.size })
            }
        } ?: Text("No data loaded.")
    }
}

@Composable
fun TrialModeButtons(onNext: () -> Unit) {
    Column {
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("✅ Confidently Correct")
        }
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("✔️ Correct")
        }
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("❌ Incorrect")
        }
        Button(onClick = onNext, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Text("🤷 No Idea")
        }
    }
}

@Composable
fun LearningModeButtons(onNext: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = onNext) { Text("✅ Done") }
        Button(onClick = onNext) { Text("➕ Show Me Next Time") }
    }
}
