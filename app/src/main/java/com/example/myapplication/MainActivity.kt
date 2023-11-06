package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asociar elementos de la interfaz de usuario con variables
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)
        registerLink = findViewById(R.id.register_link)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Aquí debes agregar la lógica de validación de inicio de sesión y la acción correspondiente
            // Puedes agregar llamadas a funciones o utilizar una API para verificar las credenciales.

            // Ejemplo simple para verificar credenciales
            if (username == "admin" && password == "123") {
                val intent = Intent(this, ProductList::class.java)
            // Iniciar la actividad "ProductsList"
                startActivity(intent)
            } else {
                Toast.makeText(this, "ACCESO DENEGADO", Toast.LENGTH_LONG).show()

            }
        }

        // Agregar un oyente de clic al enlace de registro
        registerLink.setOnClickListener {
            val intent = Intent(this, activiy_registro_usuarios::class.java)
            startActivity(intent)
            // Aquí puedes redirigir al usuario a la actividad de registro o realizar otras acciones relacionadas con el registro.
        }
    }
}