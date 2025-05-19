package com.example.quizhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizhistoryapp.util.SharedPreferencesHelper

class WelcomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Obtiene referencias a los elementos de la Activity
        val etName: EditText = findViewById(R.id.etName)
        val btnStart: Button = findViewById(R.id.btnStart)

        // El botón se activara cuando se presione
        btnStart.setOnClickListener {
            // Obtiene el texto que el usuario ingrese y se guarda en una variable
            val name = etName.text.toString().trim()

            // Se verifica que el campo texto no este vacío
            if(name.isEmpty()){
                // Muestra un menaje si campo de texto se encuentra vacío
                Toast.makeText(this, "Por favor escribe tu nombre", Toast.LENGTH_SHORT).show()
            }else{
                // Guarda el nombre de usuario en SharedPreferences
                SharedPreferencesHelper.saveUsername(this, name)

                // Crea un intent para iniciar QuizActivity y finaliza la Activity actual
                val intent = Intent(this, QuizActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}