package com.example.salvadordepessoas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etNome: EditText
    private lateinit var etIdade: EditText
    private lateinit var btSalvar: Button
    private lateinit var btListar: Button
    private lateinit var btContar: Button
    private lateinit var btDeletar: Button
    private lateinit var btAtualizar: Button
    private lateinit var btFind: Button
    private lateinit var dao: PessoaDAO



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = PessoaDAO(this)

        this.etNome = findViewById(R.id.etNome)
        this.etIdade = findViewById(R.id.etIdade)
        this.btSalvar = findViewById(R.id.btSalvar)
        this.btListar = findViewById(R.id.btListar)
        this.btContar = findViewById(R.id.btContar)
        this.btFind = findViewById(R.id.btFind)
        this.btDeletar = findViewById(R.id.btDeletar)
        this.btAtualizar = findViewById(R.id.btAtualizar)

        this.btSalvar.setOnClickListener{ salvar() }
        this.btListar.setOnClickListener{ listar() }
        this.btContar.setOnClickListener{ contar() }
        this.btFind.setOnClickListener{ procurar() }
        this.btDeletar.setOnClickListener{ deletar() }
        this.btAtualizar.setOnClickListener{ atualizar() }
    }

    fun salvar(){
        val nome = this.etNome.text.toString()
        val idade = this.etIdade.text.toString().toInt()
        val pessoa = Pessoa(nome, idade)
        this.dao.insert(pessoa)
        Toast.makeText(this, "Pessoa ${pessoa.nome} salva", Toast.LENGTH_LONG).show()
    }

    fun listar(){
        Log.i("APP_SALVADOR", this.dao.select().toString())
    }

    fun contar(){
        Log.i("APP_SALVADOR", this.dao.count().toString())
    }

    fun procurar(){
        Log.i("APP_SALVADOR", this.dao.find(1).toString())
    }
    fun deletar(){
        Log.i("APP_SALVADOR", this.dao.delete(3).toString())
    }

    fun atualizar(){
        Log.i("APP_SALVADOR", this.dao.update(Pessoa(5,"Jose",20)).toString())
    }
}