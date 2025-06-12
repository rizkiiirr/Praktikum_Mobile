package com.example.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.movie.data.local.MovieDatabase
import com.example.movie.data.remote.ApiService
import com.example.movie.data.repository.MovieRepositoryImpl
import com.example.movie.domain.usecase.GetPopularMoviesUseCase
import com.example.movie.ui.screen.MovieDetailScreen
import com.example.movie.ui.screen.MovieListScreen
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.viewmodel.MovieViewModel
import com.example.movie.ui.viewmodel.MovieViewModelFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        val apiService = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)

        val movieDatabase = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java, "movie_database"
        ).build()

        val movieRepository = MovieRepositoryImpl(apiService, movieDatabase)
        val getPopularMoviesUseCase = GetPopularMoviesUseCase(movieRepository)
        val viewModelFactory = MovieViewModelFactory(getPopularMoviesUseCase)

        setContent {
            MovieTheme {
                val navController = rememberNavController()
                val viewModel: MovieViewModel = viewModel(factory = viewModelFactory)
                val state by viewModel.state.collectAsState()

                NavHost(
                    navController = navController,
                    startDestination = "movie_list"
                ) {
                    composable("movie_list") {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                TopAppBar(
                                    title = { Text("Popular Movies") }
                                )
                            }
                        ) { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                if (state.isLoading) {
                                    CircularProgressIndicator()
                                }

                                state.error?.let { error ->
                                    Text(
                                        text = "Terjadi Kesalahan:\n$error",
                                        modifier = Modifier.padding(16.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                if (!state.isLoading && state.error == null) {
                                    MovieListScreen(
                                        movies = state.movies,
                                        onDetailClick = { movieId ->
                                            navController.navigate("movie_detail/$movieId")
                                        }
                                    )
                                }
                            }
                        }
                    }

                    composable(
                        route = "movie_detail/{movieId}",
                        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt("movieId")
                        val selectedMovie = state.movies.find { it.id == movieId }

                        if (selectedMovie != null) {
                            MovieDetailScreen(
                                movie = selectedMovie,
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        } else {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Film tidak ditemukan.")
                            }
                        }
                    }
                }
            }
        }
    }
}