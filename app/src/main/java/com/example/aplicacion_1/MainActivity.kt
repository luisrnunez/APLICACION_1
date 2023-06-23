package com.example.aplicacion_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    private lateinit var edtCedula: EditText
    private lateinit var edtNombres: EditText
    private lateinit var edtFechaNacimiento: EditText
    private lateinit var edtCiudad: EditText
    private lateinit var rgGenero: RadioGroup
    private lateinit var edtCorreo: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var btnLimpiar: Button
    private lateinit var btnEnviar: Button


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCedula = findViewById(R.id.edtCedula)
        edtNombres = findViewById(R.id.edtNombres)
        edtFechaNacimiento = findViewById(R.id.edtFechaNacimiento)
        edtCiudad = findViewById(R.id.edtCiudad)
        edtCorreo = findViewById(R.id.edtCorreo)
        rgGenero = findViewById(R.id.rgGenero)
        edtTelefono = findViewById(R.id.edtTelefono)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnEnviar = findViewById(R.id.btnEnviar)



        btnLimpiar.setOnClickListener {
            edtCedula.text.clear()
            edtNombres.text.clear()
            edtFechaNacimiento.text.clear()
            edtCiudad.text.clear()
            edtCorreo.text.clear()
            edtTelefono.text.clear()
        }

        btnEnviar.setOnClickListener {
            if(validacion())
            {
                val intent = Intent(this, MainActivity2::class.java)
                val b = Bundle()


                b.putString("cedula", edtCedula.text.toString())
                b.putString("nombres", edtNombres.text.toString())
                b.putString("fecha", edtFechaNacimiento.text.toString())
                b.putString("ciudad", edtCiudad.text.toString())
                b.putString("genero", when (findViewById<RadioGroup>(R.id.rgGenero).checkedRadioButtonId) {
                R.id.rMasculino -> "Masculino"
                R.id.rFemenino -> "Femenino"
                else -> ""
                })
                b.putString("correo", edtCorreo.text.toString())
                b.putString("telefono", edtTelefono.text.toString())

                intent.putExtras(b)
                startActivity(intent)
            }
        }
    }



    private fun btnEnviar() {

        if(!validacion())
        {
            return
        }

    }

    private fun validacion(): Boolean {
        val cedula = edtCedula.text.toString()
        val nombres = edtNombres.text.toString()
        val fechaNacimiento = edtFechaNacimiento.text.toString()
        val ciudad = edtCiudad.text.toString()
        val correo = edtCorreo.text.toString()
        val telefono = edtTelefono.text.toString()

        if (cedula.isEmpty() || cedula.length > 10 || !Pattern.matches("\\d+", cedula)) {
            edtCedula.error = "Ingrese una cédula válida de hasta 10 dígitos."
            return false
        }

        if (nombres.isEmpty() || nombres.length > 500) {
            edtNombres.error = "Ingrese un nombre válido de hasta 500 caracteres."
            return false
        }

        if (fechaNacimiento.isEmpty()) {
            edtFechaNacimiento.error = "Ingrese una fecha de nacimiento válida."
            return false
        }

        if (ciudad.isEmpty() || ciudad.length > 200) {
            edtCiudad.error = "Ingrese una ciudad válida de hasta 200 caracteres."
            return false
        }


        if (correo.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            edtCorreo.error = "Ingrese un correo electrónico válido."
            return false
        }

        if (rgGenero.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Seleccione un género.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (telefono.isEmpty() || !Pattern.matches("\\d+", telefono) || telefono.length != 10) {
            edtTelefono.error = "Ingrese un número de teléfono válido de 10 dígitos."
            return false
        }
        return true
    }
}

