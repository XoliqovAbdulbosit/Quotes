package com.example.myapplication.model

import com.example.myapplication.api.Quote
import com.example.myapplication.api.Quotes
import com.example.myapplication.api.Service

class HomeModel(val api: Service) {
    suspend fun getQuotes(): Quotes {
        return api.getQuotes()
    }

    suspend fun getRandomQuote(): Quote {
        return api.getRandomQuote()
    }

    suspend fun getQuote(id: Int): Quote {
        return api.getQuote(id)
    }
}