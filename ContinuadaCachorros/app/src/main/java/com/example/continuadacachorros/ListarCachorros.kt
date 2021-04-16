package com.example.continuadacachorros

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarCachorros : AppCompatActivity() {

    lateinit var tvIndicado:TextView
    lateinit var tvNaoIndicado:TextView
    lateinit var tvTotal:TextView

    var contadorNaoIndicado = 0
    var contadorIndicado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_cachorros)

        val layoutLista: LinearLayout = findViewById(R.id.layout_listaCachorro)

        tvIndicado = findViewById(R.id.tv_indicado)
        tvNaoIndicado = findViewById(R.id.tv_naoIndicado)
        tvTotal = findViewById(R.id.tv_total)

        val apiCachorros = ConexaoApiCachorros.criar()
        apiCachorros.get().enqueue(object : Callback<List<Cachorro>> {

            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {

                response.body()?.forEach {

                    val tvCachorro = TextView(baseContext)
                    if (it.indicadoCriancas){
                        tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - Preço médio: ${it.precoMedio} - Indicado para crianças: ${it.indicadoCriancas}"
                        contadorIndicado++
                    } else {
                        tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - Preço médio: ${it.precoMedio} - Indicado para crianças: ${it.indicadoCriancas}"
                        contadorNaoIndicado++
                    }

                    tvCachorro.setTextColor(Color.parseColor("#9911AA"))

                    layoutLista.addView(tvCachorro)
                    preencherNumeros()
                }

            }

            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }

        })

        val preferencias = getSharedPreferences("LIMPEZA", MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putInt("id", (contadorIndicado + contadorNaoIndicado) - 1)
        editor.commit()

    }

    fun preencherNumeros() {
        tvIndicado.text = "Cachorros indicados para crianças:" + contadorIndicado
        tvNaoIndicado.text = "Cachorros indicados para crianças:" + contadorNaoIndicado
        tvTotal.text = "Cachorros indicados para crianças:" + (contadorIndicado + contadorNaoIndicado)
    }
}