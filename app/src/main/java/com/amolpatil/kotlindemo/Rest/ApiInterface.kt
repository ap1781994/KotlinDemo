package com.amolpatil.kotlindemo.Rest

import com.google.gson.JsonArray

import retrofit2.Call

import retrofit2.http.GET


interface ApiInterface {


    @GET("users")
    fun getUser(): Call<JsonArray>


    @GET("users")
    fun dasdsds(): Call<JsonArray>
}