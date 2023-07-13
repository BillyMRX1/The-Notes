package com.mrx.notes.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrx.notes.R
import com.mrx.notes.components.HomeTopAppBar
import com.mrx.notes.components.NoteList
import com.mrx.notes.navigation.Screen
import com.mrx.notes.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val fabAddNotes = {
        navController.navigate(route = Screen.CreateNotes.route)
    }

    viewModel.getAllNotes()

    Scaffold(
        topBar = {
            HomeTopAppBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = fabAddNotes,
                modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
            ) {
                Icon(Icons.Filled.Add, stringResource(R.string.content_description_create_notes))
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NoteList(
                viewModel.allNotes.observeAsState().value,
                navController,
                viewModel,
                viewModel.showDialog.observeAsState().value
            )
        }
    }
}