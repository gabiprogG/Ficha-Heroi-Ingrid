package com.example.fichaheroiingrid

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONObject

class Ficha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ficha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nomeFicha = findViewById<TextView>(R.id.nome_ficha)
        val imagemFicha = findViewById<ImageView>(R.id.imagem_ficha)
        val arquetipoFicha = findViewById<TextView>(R.id.arquetipo_ficha)
        val poderesFicha = findViewById<TextView>(R.id.poderes_ficha)
        val editarFicha = findViewById<Button>(R.id.editar_ficha)

        val imagens = listOf(
            R.drawable.vilao,
            R.drawable.heroina,
            R.drawable.antiheroi
        )

        val jsonString = intent.getStringExtra("personagemJSON")

        if (jsonString != null) {
            val json = JSONObject(jsonString)
            val codinome = json.getString("nome_heroi")
            val arquetipo = json.getString("arquetipo")

            val habilidades = mutableListOf<String>()

            val jsonHabilidades = json.optJSONArray("habilidades")

            if (jsonHabilidades != null) {

                for (i in 0 until jsonHabilidades.length()) {
                    habilidades.add(jsonHabilidades.optString(i))
                }
            }

            poderesFicha.text = if (habilidades.isNotEmpty()) {
                "Ele tem as habilidades de: ${habilidades.joinToString(", ")}"
            } else {
                "Ele não tem habilidades"
            }
            val imagemIndice = json.getInt("imagemIndice")

            nomeFicha.text = codinome

            arquetipoFicha.text = "O personagem " + codinome + " tem o arquetipo " + arquetipo

            if (imagemIndice in imagens.indices) {
                imagemFicha.setImageResource(imagens[imagemIndice])
            }
            val layout = findViewById<ConstraintLayout>(R.id.main)

            when (arquetipo) {
                "Herói" -> layout.setBackgroundColor(ContextCompat.getColor(this, R.color.heroi))
                "Anti-Herói" -> layout.setBackgroundColor(ContextCompat.getColor(this, R.color.anti_heroi))
                "Vilão" -> layout.setBackgroundColor(ContextCompat.getColor(this, R.color.vilao))
                else -> layout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    }
        }

        editarFicha.setOnClickListener {
            finish()
        }

        val fechar_app = findViewById<Button>(R.id.fechar)

        fechar_app.setOnClickListener {
            finishAffinity()
        }
    }
}