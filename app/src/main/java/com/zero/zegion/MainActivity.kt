package com.zero.zegion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var textView: TextView
    private lateinit var handlerAnimation: Handler
    private lateinit var mainButton: ImageButton
    private lateinit var rippleButton: View
    private lateinit var animationContainer: View
    private lateinit var textToSpeech: TextToSpeech
    private var string = ""
    private var isSpeaking = false
    private var lastPrompt = ""
    private var prompt = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the TextView and other views
        textView = findViewById(R.id.text_view)
        animationContainer = findViewById(R.id.animation_container)
        mainButton = findViewById(R.id.main_button)
        rippleButton = findViewById(R.id.ripple_button)
        textToSpeech = TextToSpeech(this, this)

        // Initialize the SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        // Initialize the handlerAnimation
        @Suppress("DEPRECATION")
        handlerAnimation = Handler()

        val result = textToSpeech.setLanguage(Locale.US)

        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show()
        }

        val sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val apiKey = sharedPreferences.getString("GeminiApiKey", "")

        val generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = apiKey ?: "" // Use an empty string if API key is not found
        )

        // Set up the RecognitionListener
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                Toast.makeText(this@MainActivity, "Listening", Toast.LENGTH_SHORT).show()
            }

            override fun onBeginningOfSpeech() {
                // Speech input started
            }

            override fun onRmsChanged(rmsdB: Float) {
                // The sound level in the audio stream has changed
            }

            override fun onBufferReceived(buffer: ByteArray?) {
                // More sound has been received
            }

            override fun onEndOfSpeech() {
                stopPulse()
            }

            override fun onError(error: Int) {
                textView.text = getString(R.string.speech_error)
                Toast.makeText(this@MainActivity, getString(R.string.speech_error), Toast.LENGTH_SHORT).show()
                stopPulse()
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val current = LocalTime.now()
                var prompt = "You are an AI assistant, so whenever a command is given to you, check if the command is within a set of given parameters. If the command is not within the given parameters, then say that the described function is not known. The available functions right now are camera and time. Make the answer short but make it feel natural response. The time is $current The user command is: "

                if (!matches.isNullOrEmpty()) {
                    prompt += matches[0].toTitleCase()

                    // Launch a coroutine to handle the API call
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            // Call generateContent in a coroutine
                            val response = generativeModel.generateContent(prompt)

                            // Update the UI on the main thread
                            withContext(Dispatchers.Main) {
                                string = response.text.toString()
                                typingAnimation(textView, string) {}
                                toggleSpeech(string)
                            }
                        } catch (e: Exception) {
                            // Handle errors
                            withContext(Dispatchers.Main) {
                                textView.text = getString(R.string.speech_error)
                                Toast.makeText(this@MainActivity, getString(R.string.speech_error), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                stopPulse()
            }

            override fun onPartialResults(partialResults: Bundle?) {
                // Partial recognition results
            }

            override fun onEvent(eventType: Int, params: Bundle?) {
                // Events related to the speech recognition
            }
        })

        // Set up animations for views
        animationContainer.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                val diameter = view.width.coerceAtMost(view.height)
                outline.setOval(0, 0, diameter, diameter)
            }
        }
        animationContainer.clipToOutline = true

        mainButton.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                val diameter = view.width.coerceAtMost(view.height)
                outline.setOval(0, 0, diameter, diameter)
            }
        }
        mainButton.clipToOutline = true

        // Set click listener for the button
        mainButton.setOnClickListener {
            isSpeaking = true
            toggleSpeech("")
            startListening()
            ripplerunner.run()
        }
    }

    private var ripplerunner = object : Runnable {
        override fun run() {
            rippleButton.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000).withEndAction {
                rippleButton.scaleX = 1f
                rippleButton.scaleY = 1f
                rippleButton.alpha = 1f
            }
            handlerAnimation.postDelayed(this, 1500)
        }
    }

    private fun stopPulse() {
        handlerAnimation.removeCallbacks(ripplerunner)
    }

    private fun startListening() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.startListening(intent)
    }

    private fun typingAnimation(textView: TextView, string: String, onComplete: () -> Unit) {
        val n = string.length
        val time = 1250 / n
        val stringBuilder = StringBuilder()

        Thread {
            for (i in string) {
                stringBuilder.append(i)
                Thread.sleep(time.toLong())
                runOnUiThread {
                    textView.text = stringBuilder.toString()
                }
            }
            // Call the onComplete function after the loop ends
            runOnUiThread {
                onComplete()
            }
        }.start()
    }

    private fun toggleSpeech(string: String) {

        if (isSpeaking) {
            // Stop speaking
            textToSpeech.stop()
            isSpeaking = false
        } else {
            if (string.isNotEmpty()) {
                val params = Bundle()
                textToSpeech.speak(string, TextToSpeech.QUEUE_FLUSH, params, "UniqueID")
                isSpeaking = true
            } else {
                Toast.makeText(this, "Text is empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language for TTS

            textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    // Called when the TTS starts speaking
                    isSpeaking = true
                }

                override fun onDone(utteranceId: String?) {
                    // Called when the TTS finishes speaking
                    isSpeaking = false
                    if ("camera" in prompt.lowercase()) {
                        // Create an Intent to start the CameraActivity
                        val intent = Intent(this@MainActivity, CameraActivity::class.java)
                        startActivity(intent)
                    }
                }

                @Deprecated("Deprecated in Java", ReplaceWith("TODO(\"Not yet implemented\")"))
                override fun onError(p0: String?) {
                    TODO("Not yet implemented")
                }
            })
        } else {
            Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

}

fun String.toTitleCase(): String {
    return split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    } }
}
