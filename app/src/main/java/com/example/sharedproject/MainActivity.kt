package com.example.sharedproject

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var sharedName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtText: EditText = findViewById(R.id.edtText)
        sharedName = findViewById(R.id.textView2)
        val btn: Button = findViewById(R.id.btn)

        btn.setOnClickListener {
            val enteredString: String = edtText.text.toString()
            SaveSharedName(enteredString)
        }

        getValue()
    }

    @SuppressLint("CommitPrefEdits")
    private fun SaveSharedName(name: String) {
        // salvando e compartilhando informacoes
        // para salvar e compartilhar informacoes e necessario criarmos e manipularmos objetos
        // do tipo chave e valor, onde poderemos acessar e manipular as informacoes.

        // definimos uma variavel do tipo SharedPreferences onde ela acessara os dados compartilhados
        // passada em modo privado mantendo o encapsulamento de informacoes
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("userName", MODE_PRIVATE)

        // definindo uma variavel edit para podermos modificar o que vamos compartilhar
        val edit: SharedPreferences.Editor = sharedPreferences.edit()

        // passando chave e valor para nossa informacao
        edit.putString("name", name)
        // usando o commit os dados serao persistidos de forma direta enquanto o apply faz em segundo plano
        // edit.commit()
        edit.apply()
    }

    // funcao de acesso do valor compartilhado
    @SuppressLint("SetTextI18n")
    private fun getValue() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("userName", MODE_PRIVATE)
        // definindo variavel String ou nula para caso o resultado nao exista
        val result: String? = sharedPreferences.getString("name", "")
        if (result.isNullOrEmpty()) {
            sharedName.text = "Sem informações compartilhadas"
        }
        sharedName.text = result
    }
}