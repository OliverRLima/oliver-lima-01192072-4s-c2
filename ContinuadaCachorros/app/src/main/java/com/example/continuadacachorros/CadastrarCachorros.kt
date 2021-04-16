package com.example.continuadacachorros

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CadastrarCachorros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_cachorros)
    }

    fun realizarCadastro(view: View) {
        val apiCachorros = ConexaoApiCachorros.criar()

        val etRaca: EditText = findViewById(R.id.et_raca)
        val etPreco: EditText = findViewById(R.id.et_preco)
        val swIndicado: Switch = findViewById(R.id.sw_indicado)

        val cachorroCriado = Cachorro(0, etRaca.text.toString(), etPreco.text.toString().toDouble(), swIndicado.isChecked)

        val tvMensagem:TextView = findViewById(R.id.tv_mensagem)
        val ivFoto:ImageView = findViewById(R.id.iv_foto)

        tvMensagem.visibility = View.INVISIBLE
        ivFoto.visibility = View.INVISIBLE

        apiCachorros.post(cachorroCriado).enqueue(object : retrofit2.Callback<Cachorro> {
            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 201) {
                    tvMensagem.visibility = View.VISIBLE
                    ivFoto.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()

            }
        })

    }

}

