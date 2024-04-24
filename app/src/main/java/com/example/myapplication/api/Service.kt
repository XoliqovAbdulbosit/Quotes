package com.example.myapplication.api

import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("/quotes")
    suspend fun getQuotes(): Quotes

    @GET("/quotes/random")
    suspend fun getRandomQuote(): Quote

    @GET("/quotes/{id}")
    suspend fun getQuote(@Path("id") id: Int): Quote
}