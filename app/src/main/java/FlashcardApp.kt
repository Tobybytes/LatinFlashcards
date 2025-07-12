package com.example.latinflashcards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FlashcardApp(vocabList: List<VocabEntry>) {
    var mode by remember { mutableStateOf<String?>(null) }

    when (mode) {
        null -> ModeSelector(
            onTrialModeSelected = { mode = "trial" },
            onLearningModeSelected = { mode = "learning" }
        )
        "trial" -> TrialModeScreen(vocabList)
        "learning" -> LearningModeScreen(vocabList)
    }
}

@Composable
fun ModeSelector(
    onTrialModeSelected: () -> Unit,
    onLearningModeSelected: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onTrialModeSelected, modifier = Modifier.padding(16.dp)) {
            Text("Trial Mode")
        }
        Button(onClick = onLearningModeSelected, modifier = Modifier.padding(16.dp)) {
            Text("Learning Mode")
        }
    }
}

@Composable
fun TrialModeScreen(vocabList: List<VocabEntry>) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentCard = if (vocabList.isNotEmpty()) vocabList[currentIndex] else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentCard == null) {
            Text("No vocabulary found")
        } else {
            Flashcard(vocabEntry = currentCard)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { /* TODO: confidently correct logic */ }) {
                    Text("Confidently Correct")
                }
                Button(onClick = { /* TODO: correct logic */ }) {
                    Text("Correct")
                }
                Button(onClick = { /* TODO: incorrect logic */ }) {
                    Text("Incorrect")
                }
                Button(onClick = { /* TODO: no idea logic */ }) {
                    Text("No Idea")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            NavigationButtons(
                currentIndex = currentIndex,
                listSize = vocabList.size,
                onPrevious = { currentIndex = (currentIndex - 1).coerceAtLeast(0) },
                onNext = { currentIndex = (currentIndex + 1).coerceAtMost(vocabList.lastIndex) }
            )
        }
    }
}

@Composable
fun LearningModeScreen(vocabList: List<VocabEntry>) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentCard = if (vocabList.isNotEmpty()) vocabList[currentIndex] else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentCard == null) {
            Text("No vocabulary found")
        } else {
            Flashcard(vocabEntry = currentCard)

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = { /* TODO: done logic */ }) {
                    Text("Done")
                }
                Button(onClick = { /* TODO: show me next time logic */ }) {
                    Text("Show Me Next Time")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            NavigationButtons(
                currentIndex = currentIndex,
                listSize = vocabList.size,
                onPrevious = { currentIndex = (currentIndex - 1).coerceAtLeast(0) },
                onNext = { currentIndex = (currentIndex + 1).coerceAtMost(vocabList.lastIndex) }
            )
        }
    }
}

@Composable
fun NavigationButtons(
    currentIndex: Int,
    listSize: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row {
        Button(
            onClick = onPrevious,
            enabled = currentIndex > 0
        ) {
            Text("Previous")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = onNext,
            enabled = currentIndex < listSize - 1
        ) {
            Text("Next")
        }
    }
}

@Composable
fun Flashcard(vocabEntry: VocabEntry) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = vocabEntry.latin,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = vocabEntry.english,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Part of Speech: ${vocabEntry.speechPart ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Chapter: ${vocabEntry.chapter ?: "N/A"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
