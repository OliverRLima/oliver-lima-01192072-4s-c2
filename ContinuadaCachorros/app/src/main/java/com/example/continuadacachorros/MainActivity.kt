package com.example.continuadacachorros

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // Aqui utilizo o SharedPreferences para excluir o ultimo registro adicionado na api, para caso a lista de cachorros estiver cheia
    // então separei dois métodos que recuperam e deletam o ultimo id, para que assim não de problema no cadastro
    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferencias = getSharedPreferences("LIMPEZA", MODE_PRIVATE)

        deletarRegistro(pegarValorId())
    }

    fun cadastrar(view: View) {
        deletarRegistro(pegarValorId())

        val telaCadastro = Intent(this, CadastrarCachorros::class.java)
        startActivity(telaCadastro)
    }

    fun listar(view: View) {
        deletarRegistro(pegarValorId())

        val telaListar = Intent(this, ListarCachorros::class.java)
        startActivity(telaListar)
    }

    fun deletarRegistro(id:Int){
        println(id)
        val apiCachorro = ConexaoApiCachorros.criar()

        apiCachorro.delete(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
            }
        })

        val editor = preferencias.edit()
        editor.remove("id")
        editor.commit()
    }

    fun pegarValorId(): Int{
        var ultimoId = preferencias.getInt("id", 0)
        return ultimoId
    }
}