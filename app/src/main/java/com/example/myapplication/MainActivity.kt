package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.api.Client
import com.example.myapplication.api.Service
import com.example.myapplication.model.HomeModel
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.view.HomeView
import com.example.myapplication.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val api = Client.getInstance().create(Service::class.java)
                val model = HomeModel(api)
                val viewmodel = HomeViewModel(model)
                HomeView(viewmodel)
            }
        }
    }
}