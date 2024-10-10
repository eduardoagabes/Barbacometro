package com.eduardoagabes.barbacometro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.barbacometro.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ctdAdultos = binding.tieAdultos
        val ctdNinos = binding.tieNinos
        val duracionBarbacoa = binding.tieDuracion

        binding.btnCalcular.setOnClickListener {

            val adultosStr = ctdAdultos.text
            val ninosStr = ctdNinos.text
            val duracionStr = duracionBarbacoa.text

            if (adultosStr?.isEmpty() == true || ninosStr?.isEmpty() == true ||
                duracionStr?.isEmpty() == true
            ) {

                Snackbar.make(
                    ctdAdultos,
                    "Rellene todos los campos",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {

                val adultos = adultosStr.toString().toInt()
                val ninos = ninosStr.toString().toInt()
                val duracion = duracionStr.toString().toInt()

                val totalCarne = calcularCarne(adultos, ninos, duracion)
                val totalCerveza = calcularCerveza(adultos, duracion)
                val totalRefresco = calcularRefresco(adultos, ninos, duracion)


                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("TOTAL_CARNE", totalCarne)
                intent.putExtra("TOTAL_CERVEZA", totalCerveza)
                intent.putExtra("TOTAL_REFRESCO", totalRefresco)
                startActivity(intent)
            }
        }
    }

    private fun calcularCarne(adultos: Int, ninos: Int, duracion: Int): Double {
        val carnePorAdulto = if (duracion > 6) 500 else 300
        val carnePorCrianca = if (duracion > 6) 200 else 100
        return (adultos * carnePorAdulto + ninos * carnePorCrianca).toDouble()
    }

    private fun calcularCerveza(adultos: Int, duracion: Int): Int {
        return if (duracion > 6) adultos * 5 else adultos * 3
    }

    private fun calcularRefresco(adultos: Int, ninos: Int, duracion: Int): Double {
        val refrescoPorPessoa = if (duracion > 6) 1.5 else 1.0
        return (adultos + ninos) * refrescoPorPessoa
    }
}