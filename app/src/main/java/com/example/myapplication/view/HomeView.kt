package com.example.myapplication.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.viewmodel.HomeViewModel

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewmodel: HomeViewModel) {
    val quotes = viewmodel.quotes.collectAsState().value
    val quote = viewmodel.quote.collectAsState().value
    var id by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("Home") }
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Random",
            selectedIcon = Icons.Filled.Edit,
            unselectedIcon = Icons.Outlined.Edit,
        ),
        BottomNavigationItem(
            title = "Get",
            selectedIcon = Icons.Filled.Build,
            unselectedIcon = Icons.Outlined.Build,
        ),
    )

    Scaffold(bottomBar = {
        NavigationBar(containerColor = Color.White) {
            items.forEachIndexed { _, item ->
                NavigationBarItem(selected = state == item.title, onClick = {
                    state = item.title
                }, label = {
                    Text(text = item.title, color = Color.Black)
                }, icon = {
                    Icon(
                        imageVector = if (state == item.title) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title,
                        tint = Color.Black,
                    )
                }, colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Gray))
            }
        }
    }) { padding ->
        when (state) {
            "Home" -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(5.dp)
                ) {
                    items(quotes.quotes) {
                        Column(modifier = Modifier.padding(vertical = 7.dp)) {
                            Text(
                                "${it.id}. ${it.quote}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(it.author, fontSize = 17.sp)
                        }
                    }
                }
            }

            "Random" -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(7.dp)
                ) {
                    Text(
                        "${quote.id}. ${quote.quote}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(quote.author, fontSize = 17.sp)
                }
            }

            "Get" -> {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(7.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = id,
                        onValueChange = {
                            id = it
                            if (id != "") viewmodel.getQuote(id.toInt())
                        })
                    Text(
                        "${quote.id}. ${quote.quote}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(quote.author, fontSize = 17.sp)
                }
            }
        }
    }
}