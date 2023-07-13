package com.mrx.notes.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object CreateNotes : Screen("create_notes")
    object EditNotes : Screen("edit_notes")
}