package com.example.scrollablecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scrollablecompose.uiScreen.DetailScreen
import com.example.scrollablecompose.ui.theme.ScrollableComposeTheme
import com.example.scrollablecompose.uiComponent.ListScreen
import com.example.scrollablecompose.viewModel.ChocolateViewModel
import com.example.scrollablecompose.viewModel.ChocolateViewModelFactory
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel: ChocolateViewModel = viewModel(
                factory = ChocolateViewModelFactory("")
            )

            ScrollableComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "list",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("list") {
                            val chocolateList by viewModel.chocolateList.collectAsState()
                            ListScreen(
                                chocolates = chocolateList,
                                onDetailClicked = {
                                    Timber.d("Navigating to Detail of: ${it.title}")
                                    navController.navigate("detail/${it.no}")
                                }
                            )
                        }

                        composable("detail/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                            DetailScreen(id = id, viewModel = viewModel)
                        }
                    }
                }
            }

        }
    }
}

