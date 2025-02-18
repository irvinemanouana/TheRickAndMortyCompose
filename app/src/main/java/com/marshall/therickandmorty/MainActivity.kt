package com.marshall.therickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.marshall.therickandmorty.character.domain.Character
import com.marshall.therickandmorty.character.presentation.CharacterDetailsScreen
import com.marshall.therickandmorty.character.presentation.CharacterListScreen
import com.marshall.therickandmorty.ui.theme.TheRickAndMortyTheme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheRickAndMortyTheme {
                KoinAndroidContext {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "characterList"
                    ) {
                        composable(route = "characterList") {
                            CharacterListScreen(onNavigateToDetails = {
                                selectedCharacter ->
                                navController.navigate(route = selectedCharacter)
                            })
                        }
                        composable<Character> {
                            backStackEntry ->
                            val character = backStackEntry.toRoute<Character>()
                            CharacterDetailsScreen(character = character)
                        }
                    }
                }
            }
        }
    }
}