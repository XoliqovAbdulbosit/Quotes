package com.example.myapplication.api

data class Quotes(
    val limit: Int, val quotes: List<Quote>, val skip: Int, val total: Int
)