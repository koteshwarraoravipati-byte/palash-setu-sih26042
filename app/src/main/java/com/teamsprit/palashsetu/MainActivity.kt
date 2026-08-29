package com.teamsprit.palashsetu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Box

private val Navy = Color(0xFF132D4B)
private val Blue = Color(0xFF2A66AA)
private val Teal = Color(0xFF17847E)
private val Orange = Color(0xFFE67E22)
private val PaleBlue = Color(0xFFEBF4FC)
private val PaleTeal = Color(0xFFE7F7F4)
private val PaleOrange = Color(0xFFFDF2E4)

private data class Phrase(val hindi: String, val target: String, val transliteration: String, val approved: Boolean = false)
private data class Lesson(val title: String, val subject: String, val outcome: String, val description: String)
private data class Flashcard(val emoji: String, val hindi: String, val target: String)

private val phrases = listOf(
    Phrase("गिनकर बताओ कि कितने आम हैं।", "Santhali translation pending native review", "Pending native review"),
    Phrase("एक-एक करके गिनो।", "Santhali translation pending native review", "Pending native review"),
    Phrase("चित्र देखकर शब्द बोलो।", "Santhali translation pending native review", "Pending native review"),
    Phrase("ध्यान से सुनो और दोहराओ।", "Santhali translation pending native review", "Pending native review")
)
private val lessons = listOf(
    Lesson("Counting Objects", "Mathematics", "Counting objects", "Count familiar objects from 1 to 10 using oral prompts and pictures."),
    Lesson("Picture Talk", "Language", "Oral language", "Use pictures to build vocabulary and encourage children to speak."),
    Lesson("More or Less", "Mathematics", "More and less", "Compare two groups of familiar objects.")
)
private val cards = listOf(
    Flashcard("🥭", "आम", "Santhali word pending review"),
    Flashcard("📖", "किताब", "Santhali word pending review"),
    Flashcard("3️⃣", "तीन", "Santhali word pending review"),
    Flashcard("🏠", "घर", "Santhali word pending review")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PalashSetuApp() }
    }
}

private enum class Screen(val title: String) { HOME("Home"), LIVE("Live Class"), LESSONS("Lessons"), AIDS("Teaching Aids") }

@Composable
fun PalashSetuApp() {
    var screen by remember { mutableStateOf(Screen.HOME) }
    Scaffold(
        topBar = { Header() },
        bottomBar = {
            NavigationBar {
                Screen.entries.forEach { item ->
                    NavigationBarItem(selected = screen == item, onClick = { screen = item }, icon = { Text(iconFor(item), fontSize = 20.sp) }, label = { Text(item.title, fontSize = 10.sp) })
                }
            }
        }
    ) { padding ->
        Surface(Modifier.fillMaxSize().padding(padding), color = PaleBlue) {
            when (screen) {
                Screen.HOME -> HomeScreen { screen = Screen.LIVE }
                Screen.LIVE -> LiveScreen()
                Screen.LESSONS -> LessonsScreen()
                Screen.AIDS -> AidsScreen()
            }
        }
    }
}

private fun iconFor(screen: Screen) = when (screen) { Screen.HOME -> "⌂"; Screen.LIVE -> "◉"; Screen.LESSONS -> "▣"; Screen.AIDS -> "□" }

@Composable private fun Header() {
    Row(Modifier.fillMaxWidth().background(Navy).padding(horizontal = 18.dp, vertical = 13.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text("PALASH SETU", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Offline mother-tongue FLN • Team Sprit", color = Color(0xFFD6E7F5), fontSize = 11.sp)
        }
        Text("OFFLINE", color = Color(0xFFF2B543), fontWeight = FontWeight.Bold, fontSize = 11.sp)
    }
}

@Composable private fun HomeScreen(onLive: () -> Unit) {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Card(colors = CardDefaults.cardColors(containerColor = PaleTeal), shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.padding(18.dp)) {
                    Text("Ready for a classroom", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Navy)
                    Spacer(Modifier.height(6.dp))
                    Text("Lessons and teaching aids remain available after synchronisation—even without internet.", color = Navy)
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = onLive) { Text("Open Live Classroom") }
                }
            }
        }
        item {
            Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(16.dp)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(46.dp).clip(RoundedCornerShape(23.dp)).background(Teal), contentAlignment = Alignment.Center) { Text("✓", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold) }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) { Text("Offline content ready", fontWeight = FontWeight.Bold, color = Navy); Text("Santhali prototype pack • v0.1 • review mode", fontSize = 12.sp, color = Color.Gray) }
                    Text("SYNCED", color = Teal, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        item { Text("Today’s teaching pack", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Navy) }
        items(lessons.take(2)) { LessonRow(it) }
    }
}

@Composable private fun LessonRow(lesson: Lesson) {
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(14.dp)) {
        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(45.dp).clip(RoundedCornerShape(12.dp)).background(PaleOrange), contentAlignment = Alignment.Center) { Text(if (lesson.subject == "Mathematics") "#" else "अ", fontSize = 22.sp, color = Orange, fontWeight = FontWeight.Bold) }
            Spacer(Modifier.width(12.dp)); Column(Modifier.weight(1f)) { Text(lesson.title, fontWeight = FontWeight.Bold, color = Navy); Text(lesson.subject, fontSize = 12.sp, color = Color.Gray); Text(lesson.outcome, fontSize = 12.sp, color = Teal) }
            Text("OPEN", color = Blue, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
    }
}

private fun normaliseHindi(value: String): String = value
    .trim()
    .lowercase()
    .replace("।", "")
    .replace(Regex("\\s+"), " ")

private fun findApprovedPhrase(recognisedHindi: String): Phrase? {
    val input = normaliseHindi(recognisedHindi)
    return phrases.firstOrNull { phrase ->
        val source = normaliseHindi(phrase.hindi)
        input == source || input.contains(source) || source.contains(input)
    }
}

@Composable private fun LiveScreen() {
    var selected by remember { mutableStateOf<Phrase?>(null) }
    var recognisedHindi by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Ready for a phrase") }
    var measuredLatencyMs by remember { mutableStateOf<Long?>(null) }
    var startedAtMs by remember { mutableStateOf<Long?>(null) }
    var listening by remember { mutableStateOf(false) }
    val context = androidx.compose.ui.platform.LocalContext.current
    val recognizer = remember(context) {
        if (SpeechRecognizer.isRecognitionAvailable(context)) SpeechRecognizer.createSpeechRecognizer(context) else null
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        status = if (granted) "Microphone enabled. Press voice again to start." else "Microphone permission is required for voice input."
    }

    androidx.compose.runtime.DisposableEffect(recognizer) {
        recognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) { status = "Listening… speak a Hindi classroom prompt"; listening = true }
            override fun onBeginningOfSpeech() { status = "Listening…" }
            override fun onRmsChanged(rmsdB: Float) = Unit
            override fun onBufferReceived(buffer: ByteArray?) = Unit
            override fun onEndOfSpeech() { status = "Processing locally…"; listening = false }
            override fun onError(error: Int) { status = "Voice input unavailable (code $error). Choose a phrase instead."; listening = false }
            override fun onResults(results: Bundle?) {
                listening = false
                val text = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.firstOrNull().orEmpty()
                recognisedHindi = text
                val match = findApprovedPhrase(text)
                val elapsed = startedAtMs?.let { System.currentTimeMillis() - it }
                measuredLatencyMs = elapsed
                if (match != null) {
                    selected = match
                    status = "Matched local phrase template; native review still required"
                } else {
                    selected = null
                    status = "No approved local match. The app will not invent a translation."
                }
            }
            override fun onPartialResults(partialResults: Bundle?) = Unit
            override fun onEvent(eventType: Int, params: Bundle?) = Unit
        })
        onDispose { recognizer?.destroy() }
    }

    fun startVoiceInput() {
        if (recognizer == null) {
            status = "No speech engine is available on this device. Choose a phrase instead."
            return
        }
        startedAtMs = System.currentTimeMillis()
        measuredLatencyMs = null
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
            putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE, true)
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        }
        recognizer.startListening(intent)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Live classroom bridge", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Navy)
        Text("Hindi teacher prompt → approved Santhali classroom support", color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.padding(16.dp)) {
                Text("1  Speak or choose a classroom phrase", fontWeight = FontWeight.Bold, color = Navy)
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (context.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        } else if (listening) {
                            recognizer?.stopListening()
                            listening = false
                            status = "Voice input stopped"
                        } else startVoiceInput()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text(if (listening) "Stop listening" else "Use offline Hindi voice input") }
                Spacer(Modifier.height(6.dp))
                Text(status, fontSize = 12.sp, color = if (status.contains("not") || status.contains("unavailable")) Orange else Teal)
                if (recognisedHindi.isNotBlank()) {
                    Spacer(Modifier.height(5.dp))
                    Text("Heard: $recognisedHindi", fontSize = 13.sp, color = Navy)
                }
                measuredLatencyMs?.let { Text("Measured local recognition + lookup: ${it} ms", fontSize = 11.sp, color = Color.Gray) }
                Spacer(Modifier.height(8.dp))
                Divider()
                phrases.take(3).forEach { phrase ->
                    TextButton(onClick = { selected = phrase; status = "Selected from approved local phrase list" }) {
                        Text(phrase.hindi, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        if (selected == null) {
            Card(colors = CardDefaults.cardColors(containerColor = PaleBlue), shape = RoundedCornerShape(16.dp)) {
                Text("Your approved classroom output will appear here.\\n\\nIf speech does not match a reviewed phrase, PALASH Setu safely asks the teacher to choose a phrase instead of hallucinating a translation.", Modifier.fillMaxWidth().padding(22.dp), color = Navy, textAlign = TextAlign.Center)
            }
        } else {
            val phrase = selected!!
            Card(colors = CardDefaults.cardColors(containerColor = PaleOrange), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.fillMaxWidth().padding(18.dp)) {
                    Text("2  Classroom output", fontWeight = FontWeight.Bold, color = Navy)
                    Spacer(Modifier.height(9.dp)); Text("Hindi", fontSize = 11.sp, color = Color.Gray); Text(phrase.hindi, fontSize = 18.sp, color = Navy, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(9.dp)); Text("Santhali", fontSize = 11.sp, color = Color.Gray); Text(phrase.target, fontSize = 17.sp, color = Orange, fontWeight = FontWeight.SemiBold)
                    Text("Transliteration: ${phrase.transliteration}", fontSize = 12.sp, color = Color.Gray); Spacer(Modifier.height(10.dp)); Text("NATIVE REVIEW REQUIRED • Audio will be enabled after approved recording is added", color = Orange, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable private fun LessonsScreen() {
    LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { Text("Lesson library", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Navy); Text("Mapped to foundational literacy and numeracy outcomes", color = Color.Gray) }
        items(lessons) { lesson -> Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(15.dp)) { Column(Modifier.padding(16.dp)) { Text(lesson.title, fontWeight = FontWeight.Bold, color = Navy, fontSize = 18.sp); Text("${lesson.subject} • NIPUN outcome: ${lesson.outcome}", color = Teal, fontSize = 12.sp); Spacer(Modifier.height(5.dp)); Text(lesson.description, color = Color.DarkGray); TextButton(onClick = {}) { Text("Open lesson") } } } }
    }
}

@Composable private fun AidsScreen() {
    var generated by remember { mutableStateOf(false) }
    var index by remember { mutableStateOf(0) }
    val card = cards[index]
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Teaching aids", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Navy)
        Text("Generate bilingual worksheets and use visual flashcards offline.", color = Color.Gray); Spacer(Modifier.height(12.dp))
        Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(16.dp)) { Column(Modifier.fillMaxWidth().padding(16.dp)) { Text("Bilingual worksheet", fontWeight = FontWeight.Bold, color = Navy, fontSize = 18.sp); Text("Outcome: Counting objects • Grade 1", color = Teal, fontSize = 12.sp); Spacer(Modifier.height(8.dp)); if (generated) { Text("Worksheet preview", color = Blue, fontWeight = FontWeight.Bold); Text("गिनकर बताओ कि कितने आम हैं।", fontSize = 16.sp); Text("Santhali instruction appears here after native review.", fontSize = 13.sp, color = Color.Gray); Text("○  ○  ○     ○  ○", fontSize = 30.sp, color = Orange) } else Text("Ready to generate from the selected learning outcome.", color = Color.Gray); Spacer(Modifier.height(8.dp)); Button(onClick = { generated = true }) { Text(if (generated) "Regenerate worksheet" else "Generate worksheet") } } }
        Spacer(Modifier.height(12.dp))
        Card(colors = CardDefaults.cardColors(containerColor = PaleOrange), shape = RoundedCornerShape(16.dp)) { Column(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) { Text("Visual flashcard", fontWeight = FontWeight.Bold, color = Navy, modifier = Modifier.align(Alignment.Start)); Text(card.emoji, fontSize = 56.sp); Text(card.hindi, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Navy); Text(card.target, color = Orange, fontSize = 15.sp); Text("Native review required", color = Orange, fontSize = 11.sp); Row { TextButton(onClick = { index = (index + cards.size - 1) % cards.size }) { Text("Previous") }; TextButton(onClick = { index = (index + 1) % cards.size }) { Text("Next") } } } }
    }
}
