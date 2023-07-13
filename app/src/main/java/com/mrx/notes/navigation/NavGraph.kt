package com.mrx.notes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.farhan.tanvir.androidcleanarchitecture.util.Constant.EDIT_NOTES_ARGUMENT
import com.mrx.data.model.Notes
import com.mrx.notes.view.CreateNotesScreen
import com.mrx.notes.view.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.CreateNotes.route,
        ) {
            CreateNotesScreen(navController)
        }
        composable(
            route = Screen.EditNotes.route
        ) {
            val notes = navController.previousBackStackEntry?.savedStateHandle?.get<Notes>(EDIT_NOTES_ARGUMENT)
            CreateNotesScreen(
                navController,
                isEditNote = true,
                notes = notes
            )
        }
    }
}