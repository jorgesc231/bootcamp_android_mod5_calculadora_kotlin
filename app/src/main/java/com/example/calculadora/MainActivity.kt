package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
import java.util.Locale

/*
Uso de Code -> Convert Java File a Kotlin File para convertir este proyecto hecho en Java a Kotlin,
manteniendo la misma funcionalidad.
 */

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var numero1 = 0
    var numero2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.checkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> binding.btRegistro.setEnabled(isChecked));
        binding.btSuma.setOnClickListener {
            if (checkParameters()) binding.tvResult.text = String.format(
                Locale.FRANCE, "%d", numero1 + numero2
            )
        }
        binding.btResta.setOnClickListener {
            if (checkParameters()) binding.tvResult.text = String.format(
                Locale.FRANCE, "%d", numero1 - numero2
            )
        }
        binding.btMulti.setOnClickListener {
            if (checkParameters()) binding.tvResult.text = String.format(
                Locale.FRANCE, "%d", numero1 * numero2
            )
        }
        binding.btDiv.setOnClickListener {
            if (checkParameters()) {
                if (numero2 > 0) binding.tvResult.text =
                    String.format(Locale.FRANCE, "%.2f", numero1 / numero2.toFloat())
                else {
                    binding.tvResult.text = "División por 0"
                    Toast.makeText(this, "No se puede dividir por 0", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.btExit.setOnClickListener {
            // Cerrar app
            finish() // won't exit the application, it just kills the activity
            System.exit(0)
        }
    }

    private fun checkParameters(): Boolean {
        val num1_string = binding.inputPrimero.editText!!.text.toString()
        val num2_string = binding.inputSegundo.editText!!.text.toString()
        var result = true

        // NOTA: Usa excepciones para controlar errores

        if (!num1_string.isEmpty()) {
            binding.inputPrimero.error = null
            try {
                numero1 = num1_string.toInt()
            } catch (e: Exception) {
                binding.inputPrimero.error = "Ingresa un numero valido"
                result = false
            }
        } else {
            binding.inputPrimero.error = "Debes ingresar un numero!"
            result = false
        }
        if (!num2_string.isEmpty()) {
            binding.inputSegundo.error = null
            try {
                numero2 = num2_string.toInt()
            } catch (e: Exception) {
                binding.inputSegundo.error = "Ingresa un numero valido"
                result = false
            }
        } else {
            binding.inputSegundo.error = "Debes ingresar un numero!"
            result = false
        }
        return result
    }
}