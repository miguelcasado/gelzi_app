package app.gelzi.gelziapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.gelzi.gelziapp.ui.screens.ai.AiScreen
import app.gelzi.gelziapp.ui.screens.exercises.ExercisesScreen
import app.gelzi.gelziapp.ui.screens.profile.ProfileScreen
import app.gelzi.gelziapp.ui.screens.recipes.RecipesScreen
import app.gelzi.gelziapp.ui.screens.settings.SettingsScreen
import app.gelzi.gelziapp.utils.SessionManager
import app.gelzi.gelziapp.ui.screens.calendar.CalendarScreen

@Composable
fun AppNavGraph(navController: NavHostController, innerPaddingValues: PaddingValues,sessionManager: SessionManager){
    NavHost(navController =  navController, startDestination = Destinations.recipes
    ){
        composable(Destinations.calendar){
            CalendarScreen(innerPaddingValues)
        }
        composable(Destinations.recipes){
            RecipesScreen(innerPaddingValues)
        }
        composable(Destinations.ai){
            AiScreen(innerPaddingValues)
        }
        composable(Destinations.exercises){
            ExercisesScreen(innerPaddingValues)
        }
        composable(Destinations.profile){
            ProfileScreen(innerPaddingValues)
        }
        composable(Destinations.settings) {
            SettingsScreen(navController, innerPaddingValues, sessionManager)
        }
    }
}