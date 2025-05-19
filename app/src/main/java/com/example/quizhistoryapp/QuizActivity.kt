package com.example.quizhistoryapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizhistoryapp.model.Question

class QuizActivity : AppCompatActivity() {

    // Variables de los elementos que se encuentran en la Activity
    private lateinit var progressBar: ProgressBar
    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbOption: List<RadioButton>
    private lateinit var tvResult: TextView
    private lateinit var btnSubmit: Button

    // Variables de la ProgressBar
    private var progressStatus = 0
    private val maxProgress = 60
    private val increment = 10

    // Lista de preguntas
    private val questions = listOf(
        Question(
            "¿En qué año comenzó la Segunda Guerra Mundial?",
            listOf("1935", "1939", "1941", "1945"),
            1
        ),
        Question(
            "¿Quién fue el primer emperador romano?",
            listOf("Julio César", "Augusto", "Nerón", "Trajano"),
            1
        ),
        Question(
            "¿Qué civilización construyó Machu Picchu?",
            listOf("Azteca", "Maya", "Inca", "Olmeca"),
            2
        ),
        Question(
            "¿Cuál fue la causa principal de la Revolución Francesa?",
            listOf("Colonialismo", "Desigualdad social", "Guerras religiosas", "Cambio climático"),
            1
        ),
        Question(
            "¿En qué país se originó la Revolución Industrial?",
            listOf("Francia", "Estados Unidos", "Alemania", "Reino Unido"),
            3
        ),
        Question(
            "¿Qué muro cayó en 1989 simbolizando el fin de la Guerra Fría?",
            listOf("Muro de Londres", "Muro de Berlín", "Muro de París", "Muro de Moscú"),
            1
        )
    )

    // Referncia al índice de la lista de preguntas
    private var currentIndex = 0

    // Puntuación inicial
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Obtiene una referencia de todos los elementos de la Activity
        progressBar = findViewById(R.id.progressBar)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        rbOption = listOf(
            findViewById(R.id.rbOption1),
            findViewById(R.id.rbOption2),
            findViewById(R.id.rbOption3),
            findViewById(R.id.rbOption4)
        )
        tvResult = findViewById(R.id.tvResult)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Llama a la función para mostrar una pregunta
        showQuestion()

        // Maneja el estado de la pregunta iniciando con false
        var isAnswerSubmited = false

        // Botón enviar
        btnSubmit.setOnClickListener {

            // Si la pregunta no ha sido seleccionada muestra un mensaje
            if (!isAnswerSubmited) {
                val selectedId = rgOptions.checkedRadioButtonId
                if (selectedId == -1) {
                    Toast.makeText(this, "Selecciona una repuesta", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Indice del elemento RadioButon seleccionado dentro del elemento RadioGroup
                val selectedIndex = rbOption.indexOfFirst { it.id == selectedId }
                // Respuesta de la pregunta
                val correctIndex = questions[currentIndex].answer

                // Mensaje si la respuesta es incorrecta
                val answerCorrect =
                    " La respuesta correcta era: '${questions[currentIndex].options[correctIndex]}'"

                // Si el elemento seleccionado en el RadioGroup coinside con el de la respuesta el score aumenta
                if (selectedIndex == correctIndex) {
                    score++
                    // Muestra un mensaje
                    tvResult.setTextColor(Color.GREEN)
                    // Cambia el texto del elemento Button
                    tvResult.text = getString(R.string.correct)
                } else {
                    // Si la pregunta es incorrecta Muestra un mensaje con la respuesta correcta
                    tvResult.setTextColor(Color.RED)
                    tvResult.text = getString(R.string.incorrect) + answerCorrect
                }

                // Cambia el estado de la pregunta a true
                isAnswerSubmited = true
                // Cambia el texto del elemento Button
                btnSubmit.text = getString(R.string.following)

            } else {
                // Llama a la función que maneja la ProgressBar
                functionProgresBar()
                // Aumenta el indice actual
                currentIndex++

                // Si la pregunta es menor que el tamaño de la lista de preguntas desmarca el elemento RadioButton seleccionado
                if (currentIndex < questions.size) {
                    rgOptions.clearCheck()
                    // Cambia el texto del elemeto TextView
                    tvResult.text = ""
                    tvResult.setTextColor(Color.GREEN)
                    // Muestra la siguiente pregunta
                    showQuestion()
                    // Cambia el estado de la pregunta a false
                    isAnswerSubmited = false
                    // Cambia el texto del elemento Button
                    btnSubmit.text = getString(R.string.submit)
                } else {
                    // Si ya no hay más preguntas en la lista lanza una nueca Activity para mostrar el resultado
                    val intent = Intent(this, ResultActivity::class.java)
                    // Envia a ResultActivity el score
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    // Muestra una pregunta en el elemento TextView
    fun showQuestion() {
        val question = questions[currentIndex]
        tvQuestion.text = question.text

        question.options.forEachIndexed { index, option -> rbOption[index].text = option }
    }

    // Incrementa el valor de la ProgressBar
    fun functionProgresBar() {
        if (progressStatus < maxProgress) {
            progressStatus += increment
            progressBar.progress = progressStatus
        }
    }
}