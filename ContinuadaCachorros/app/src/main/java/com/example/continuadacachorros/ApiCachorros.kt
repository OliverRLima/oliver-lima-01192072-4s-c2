package com.example.continuadacachorros

import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ApiCachorros {

    @GET("cachorros")
    fun get(): Call<List<Cachorro>>

    @DELETE("cachorros/{id}")
    fun delete (@Path("id") id:Int): Call<Void>

    @POST("cachorros")
    fun post(@Body cachorro: Cachorro): Call<Cachorro>
}