package com.example.aplicacion_1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var txtPresentacion: TextView = findViewById(R.id.txtPresentacion)
        val bundle = this.intent.extras
        txtPresentacion.setText(
            """
                 Te has inscrito con los siguientes datos: 
                 Cedula: ${bundle!!.getString("cedula")}
                 Nombres: ${bundle!!.getString("nombres")}
                 Fecha de Nacimiento: ${bundle!!.getString("fecha")}
                 Ciudad: ${bundle!!.getString("ciudad")}
                 Genero: ${bundle!!.getString("genero")}
                 Correo: ${bundle!!.getString("correo")}
                 Telefono: ${bundle!!.getString("telefono")}
                 """.trimIndent()
        )
    }
}