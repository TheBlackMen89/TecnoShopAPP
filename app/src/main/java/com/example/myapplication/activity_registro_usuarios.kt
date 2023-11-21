package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID


class activiy_registro_usuarios : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_usuarios)

        val auth = FirebaseAuth.getInstance()

        var mDatabase = FirebaseDatabase.getInstance().reference
        val usernameEditText = findViewById<EditText>(R.id.editTextText7)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword2)
        val emailEditText = findViewById<EditText>(R.id.editTextText8)
        val registerButton = findViewById<Button>(R.id.button)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            val usuario = Usuario()
            usuario.setUid(UUID.randomUUID().toString());
            usuario.setUsuario(username);
            usuario.setPassword(password);
            usuario.setCorreo(email);

            mDatabase.child("Usuario").child(usuario.uid).setValue(usuario)
                .addOnSuccessListener {
                    // Data successfully written to the database
                    Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                    // You can also navigate the user to another activity here if needed
                }
                .addOnFailureListener { e ->
                    // Failed to write data to the database
                    Toast.makeText(
                        this,
                        "El usuario no fue registrado: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

    }
}
