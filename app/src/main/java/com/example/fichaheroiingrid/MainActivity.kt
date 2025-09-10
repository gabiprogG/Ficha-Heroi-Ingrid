package com.example.fichaheroiingrid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome_heroi = findViewById<EditText>(R.id.nome_heroi)
        val gerar_perfil = findViewById<Button>(R.id.gerar_perfil)
        val prefs = getSharedPreferences("MinhaPrefs", MODE_PRIVATE)
        val textoSalvo = prefs.getString("codinome_salvo", "")
        nome_heroi.setText(textoSalvo)

        gerar_perfil.setOnClickListener {

            if (nome_heroi.text.toString().trim().isEmpty()){
                Toast.makeText(this,"O nome do personagem não pode estar vazio!", Toast.LENGTH_LONG).show()

            } else {
                val editor = prefs.edit()
                editor.putString("codinome_salvo", nome_heroi.text.toString())
                editor.apply()

                val intent = Intent(this, CriacaoHeroiActivity::class.java)

                intent.putExtra("nome_heroi", nome_heroi.text.toString().trim())
                startActivity(intent)

            }

        }





    }


}