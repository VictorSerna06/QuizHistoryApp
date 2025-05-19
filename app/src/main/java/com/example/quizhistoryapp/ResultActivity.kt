package com.example.quizhistoryapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizhistoryapp.util.SharedPreferencesHelper

class ResultActivity: AppCompatActivity() {

    // Variables de los elementos que se encuentran en la Activity
    private lateinit var tvName: TextView
    private lateinit var tvScore: TextView
    private lateinit var btnRestart: Button
    private lateinit var btnExit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Obtiene una referencia de todos los elementos de la Activity
        tvName = findViewById(R.id.tvName)
        tvScore = findViewById(R.id.tvScore)
        btnRestart = findViewById(R.id.btnRestart)
        btnExit = findViewById(R.id.btnExit)

        // Obtiene el puntaje de QuizActivity
        val score = intent.getIntExtra("SCORE", 0)

        // Cambia el color del elemento TextView
        tvName.setTextColor(Color.YELLOW)
        // Obtiene y muestra el nombre del usuario que se registro en WelcomeActivity y se guardo en SharedPreferencesHelper
        tvName.text = "${SharedPreferencesHelper.getUsername(this)}"
        // Muestra el puntaje
        tvScore.text = "Tu puntaje fue de: $score"

        // Regresa a WelcomeActivity al presionar el elemento Button
        btnRestart.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Sale de la aplicación al presionar el elemento Button
        btnExit.setOnClickListener {
            finishAffinity()
            System.exit(0)
        }
    }
}