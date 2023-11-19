package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class activiy_registro_usuarios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuarios)

        val auth = FirebaseAuth.getInstance()

        val usernameEditText = findViewById<EditText>(R.id.editTextText7)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword2)
        val emailEditText = findViewById<EditText>(R.id.editTextText8)
        val registerButton = findViewById<Button>(R.id.button)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        // Aquí puedes realizar operaciones adicionales si el registro es exitoso
                        tex
                    } else {
                        Toast.makeText(
                            this,
                            "Error al registrar usuario: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

    }

}
