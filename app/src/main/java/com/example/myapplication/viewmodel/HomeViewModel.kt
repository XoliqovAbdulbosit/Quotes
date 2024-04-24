package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.Quote
import com.example.myapplication.api.Quotes
import com.example.myapplication.model.HomeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val model: HomeModel) : ViewModel() {
    private var _quotes = MutableStateFlow<Quotes>(Quotes(0, emptyList(), 0, 0))
    val quotes = _quotes

    private var _quote = MutableStateFlow<Quote>(Quote("", 0, ""))
    val quote = _quote

    fun getQuotes() {
        viewModelScope.launch {
            _quotes.value = model.getQuotes()
        }
    }

    fun getRandomQuote() {
        viewModelScope.launch {
            _quote.value = model.getRandomQuote()
        }
    }

    fun getQuote(id: Int) {
        viewModelScope.launch {
            _quote.value = model.getQuote(id)
        }
    }

    init {
        getQuotes()
        getRandomQuote()
    }
}