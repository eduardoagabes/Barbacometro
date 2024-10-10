package com.eduardoagabes.barbacometro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.barbacometro.databinding.ActivityMainBinding
import com.eduardoagabes.barbacometro.databinding.ActivityResultBinding
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val totalCarne = intent.getDoubleExtra("TOTAL_CARNE", 0.0)
        val totalCerveza = intent.getIntExtra("TOTAL_CERVEZA", 0)
        val totalRefresco = intent.getDoubleExtra("TOTAL_REFRESCO", 0.0)

        val df = DecimalFormat("#,###")


        binding.tvCtdCarne.text = df.format(totalCarne)
        binding.tvCtdCerveza.text = totalCerveza.toString()
        binding.tvCtdRefresco.text = totalRefresco.toString()

        binding.btnNuevoCalculo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}