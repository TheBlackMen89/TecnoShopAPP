package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {


    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerLink: TextView
    private lateinit var iconomapa: ImageView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        checkLocationPermission()
        obtenerUltimaUbicacion()

        dbHelper = DatabaseHelper(this)
        // Asociar elementos de la interfaz de usuario con variables
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login_button)
        registerLink = findViewById(R.id.register_link)
        iconomapa = findViewById(R.id.iconomapa)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username == "admin" && password == "123") {
                val intent = Intent(this, ProductList::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ACCESO DENEGADO", Toast.LENGTH_LONG).show()
            }
        }
        registerLink.setOnClickListener {
            val intent = Intent(this, activiy_registro_usuarios::class.java)
            startActivity(intent)
        }
        iconomapa.setOnClickListener{
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }
        // Agregar una ubicación a la base de datos (este es un ejemplo)
        val latitude = "37.7749"
        val longitude = "-122.4194"
        val added = dbHelper.addLocation(latitude, longitude)
        if (added) {
            Toast.makeText(
                this@MainActivity,
                "Ubicación agregada correctamente",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this@MainActivity,
                "Error al agregar la ubicación",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Obtener todos los datos almacenados en la base de datos (otro ejemplo)
        val cursor = dbHelper.getAllData()
        if (cursor.count == 0) {
            // No hay datos
            return
        }

        val stringBuilder = StringBuilder()
        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val lat = cursor.getString(1)
            val lon = cursor.getString(2)
            stringBuilder.append("ID: $id, Latitude: $lat, Longitude: $lon\n")
        }
        Toast.makeText(this@MainActivity, stringBuilder.toString(), Toast.LENGTH_LONG).show()
    }

    private fun checkLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun obtenerUltimaUbicacion() {
        // Verificar si se tienen los permisos de ubicación antes de acceder a la última ubicación conocida
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        // Utiliza la latitud y longitud obtenidas
                        // ...
                    } else {
                        // La ubicación es nula, puede que no esté disponible
                        // Realizar acciones de manejo cuando la ubicación no está disponible
                    }
                }
                .addOnFailureListener { e ->
                    // Algo salió mal al intentar obtener la ubicación
                    Toast.makeText(
                        this,
                        "Error al obtener la ubicación: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            // Permiso denegado, se debe solicitar al usuario
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes iniciar la lógica relacionada con la ubicación
            } else {
                // Permiso denegado, puedes informar al usuario sobre la necesidad del permiso
            }
        }
    }


}