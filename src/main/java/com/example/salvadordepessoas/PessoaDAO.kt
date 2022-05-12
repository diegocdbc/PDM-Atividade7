package com.example.salvadordepessoas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class PessoaDAO (var context: Context) {
    var banco: BancoHelper

    init {
        this.banco = BancoHelper(this.context)
    }

    fun insert(pessoa: Pessoa){
        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)
        this.banco.writableDatabase.insert("pessoa", null, cv)
    }

    fun select(): ArrayList<Pessoa>{
        var lista = arrayListOf<Pessoa>()
        val colunas = arrayOf("id", "nome", "idade")
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, null)
        cursor.moveToFirst()
        for (i in 1 .. cursor.count){
            var id = cursor.getInt(colunas.indexOf("id"))
            var nome = cursor.getString(colunas.indexOf("nome"))
            var idade = cursor.getInt(colunas.indexOf("idade"))
            lista.add(Pessoa(id, nome, idade))
            cursor.moveToNext()
        }

        return lista
    }

    fun find(id: Int): Pessoa?{
        val colunas = arrayOf("id", "nome", "idade")
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, null)
        cursor.moveToFirst()
        for (i in 1 .. cursor.count){
            if (id == cursor.getInt(colunas.indexOf("id"))){
                var id = cursor.getInt(colunas.indexOf("id"))
                var nome = cursor.getString(colunas.indexOf("nome"))
                var idade = cursor.getInt(colunas.indexOf("idade"))
                var pessoa = Pessoa(id,nome,idade)
                return pessoa
            }

            cursor.moveToNext()
        }
        return null
    }

    fun count(): Int{
        val colunas = arrayOf("id", "nome", "idade")
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, null)
        cursor.moveToFirst()
        return cursor.count

    }

    fun delete(id: Int){
        val db = this.banco.writableDatabase
        db?.delete("pessoa","id = ?", arrayOf(id.toString()))
    }

    fun update(pessoa: Pessoa){
        val db = this.banco.writableDatabase
        val values = ContentValues().apply {
            put("nome", pessoa.nome)
        }
        val _success = db.update("pessoa", values, "id = ?", arrayOf(pessoa.id.toString())).toLong()
        //db.close()

    }
}


