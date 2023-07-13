package com.mrx.notes.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mrx.data.extensions.toStringFormat
import com.mrx.data.model.Notes
import com.mrx.notes.R
import com.mrx.notes.navigation.Screen
import com.mrx.notes.viewmodel.CreateNotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNotesScreen(
    navController: NavHostController,
    viewModel: CreateNotesViewModel = hiltViewModel(),
    isEditNote: Boolean? = false,
    notes: Notes? = null,
) {

    if (viewModel.isNoteSaved.observeAsState().value == true) {
        navController.popBackStack(route = Screen.Home.route, inclusive = false)
    }

    if (isEditNote == true) {
        viewModel.populateDataFromLocal(notes)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            stringResource(R.string.content_description_back_button),
                        )
                    }
                },
                actions = {
                    if (viewModel.isNoteValid.observeAsState().value == true) {
                        IconButton(
                            onClick = {
                                if (isEditNote == true) {
                                    viewModel.updateNotes()
                                } else {
                                    viewModel.saveNotes()
                                }
                            }
                        ) {
                            Icon(
                                Icons.Filled.Check,
                                stringResource(R.string.content_description_create_notes)
                            )
                        }
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.title.value,
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.hint_title_notes),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onValueChange = {
                        viewModel.setTitle(it)
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                Text(
                    text = viewModel.createdAt.value.toStringFormat(),
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                        .height(600.dp),
                    value = viewModel.body.value,
                    textStyle = MaterialTheme.typography.titleLarge,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.hint_body_notes),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.titleLarge
                        )
                    },
                    onValueChange = {
                        viewModel.setBody(it)
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }
        }
    }
}