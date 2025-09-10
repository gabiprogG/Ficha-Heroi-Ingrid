package com.example.fichaheroiingrid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject


class CriacaoHeroiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criacao_heroi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val boas_vindas = findViewById<TextView>(R.id.boas_vindas)
        val nome_heroi = intent.getStringExtra("nome_heroi")

        boas_vindas.text ="Tela de edição do personagem $nome_heroi!"

        val imagem = findViewById<ImageView>(R.id.imagem)

        val imagens = listOf(
            R.drawable.vilao,
            R.drawable.heroina,
            R.drawable.antiheroi
        )

        var indiceAtual = 0

        imagem.setImageResource(imagens[indiceAtual])

        imagem.setOnClickListener {
            indiceAtual = (indiceAtual+1) % imagens.size
            imagem.setImageResource(imagens[indiceAtual])
        }

       val gerar_ficha = findViewById<Button>(R.id.gerar_ficha)

        gerar_ficha.setOnClickListener {

            val radioGroup = findViewById<RadioGroup>(R.id.arquetipo)
            val selecionadoId = radioGroup.checkedRadioButtonId

            val radioSelecionado = findViewById<RadioButton>(selecionadoId)
            val valorSelecionado = radioSelecionado.text.toString()

            val resultado = Intent(this, Ficha::class.java)

            val voo = findViewById<CheckBox>(R.id.voo)
            val forca = findViewById<CheckBox>(R.id.super_forca)
            val telepatia = findViewById<CheckBox>(R.id.telepatia)
            val velocidade = findViewById<CheckBox>(R.id.velocidade)
            val rajada = findViewById<CheckBox>(R.id.rajadao)

            val habilidadesSelecionadas = mutableListOf<String>()

            if (voo.isChecked) habilidadesSelecionadas.add(voo.text.toString())
            if (forca.isChecked) habilidadesSelecionadas.add(forca.text.toString())
            if (telepatia.isChecked) habilidadesSelecionadas.add(telepatia.text.toString())
            if (velocidade.isChecked) habilidadesSelecionadas.add(velocidade.text.toString())
            if (rajada.isChecked) habilidadesSelecionadas.add(rajada.text.toString())

            val json = JSONObject()
            json.put("nome_heroi", intent.getStringExtra("nome_heroi"))
            json.put("arquetipo", valorSelecionado)
            json.put("habilidades", JSONArray(habilidadesSelecionadas))
            json.put("imagemIndice", indiceAtual)

            resultado.putExtra("personagemJSON", json.toString())

            startActivity(resultado)
           }





    }
}