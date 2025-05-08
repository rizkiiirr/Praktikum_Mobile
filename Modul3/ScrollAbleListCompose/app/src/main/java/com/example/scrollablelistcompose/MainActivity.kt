package com.example.scrollablelistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.scrollablelistcompose.data.DataProvider
import com.example.scrollablelistcompose.ui.theme.ScrollAbleListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrollAbleListComposeTheme {
                val navController = rememberNavController()
                MyApp(navController = navController)
            }
        }
    }
}

@Composable
fun MyApp(navController: androidx.navigation.NavHostController) {
    val chocolates = remember { DataProvider.chocolateList }

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                ScrollAbleListHomeContent(
                    onDetailClick = { selected ->
                        navController.navigate("detail/${selected.no}")
                    }
                )
            }
            composable(
                "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                val chocolate = chocolates.find { it.no == id }
                chocolate?.let {
                    DetailScreen(it)
                }
            }
        }
    }
}
