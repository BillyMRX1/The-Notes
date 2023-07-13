package com.mrx.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.farhan.tanvir.androidcleanarchitecture.util.Constant.EDIT_NOTES_ARGUMENT
import com.mrx.data.extensions.getValueOrZero
import com.mrx.data.extensions.toStringFormat
import com.mrx.data.model.Notes
import com.mrx.notes.R
import com.mrx.notes.navigation.Screen
import com.mrx.notes.viewmodel.HomeViewModel

@Composable
fun NoteList(
    noteList: List<Notes>?,
    navController: NavController,
    viewModel: HomeViewModel,
    showDialog: Boolean?
) {
    if (noteList.isNullOrEmpty().not()) {
        NoteListItems(noteList, navController, viewModel, showDialog)
    } else {
        Box(modifier = Modifier.padding(bottom = 64.dp)) {
            EmptyState()
        }
    }
}

@Composable
fun NoteListItems(
    noteList: List<Notes>?,
    navController: NavController,
    viewModel: HomeViewModel,
    showDialog: Boolean?
) {
    Column(
        Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        LazyColumn {
            items(noteList ?: emptyList()) { note ->
                NoteItem(note, navController, viewModel, showDialog)
            }
        }
    }
}

@Composable
fun NoteItem(
    note: Notes,
    navController: NavController,
    viewModel: HomeViewModel,
    showDialog: Boolean?
) {
    if (showDialog == true) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissAlertDialog() },
            text = {
                Text(
                    stringResource(R.string.title_alert_dialog_delete_notes),
                    style = MaterialTheme.typography.titleMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.dismissAlertDialog()
                        viewModel.deleteNotes(viewModel.noteId.value.getValueOrZero())
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { viewModel.dismissAlertDialog() }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    EDIT_NOTES_ARGUMENT,
                    note
                )
                navController.navigate(route = Screen.EditNotes.route)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.padding(16.dp).weight(1f)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = if (note.title.isEmpty().not()) note.title else note.body,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = if (note.body.isEmpty().not() && note.title.isEmpty()
                            .not()
                    ) note.body else "No text",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
                Text(
                    text = note.createdAt.toStringFormat(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    viewModel.setNoteId(note.id)
                    viewModel.showAlertDialog()
                }
            ) {
                Icon(
                    Icons.Default.Delete,
                    stringResource(R.string.content_description_back_button),
                )
            }
        }
    }
}

@Preview
@Composable
fun EmptyState() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_state))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
        ) {
            LottieAnimation(composition = composition, progress = { progress })
        }
        Text(
            text = "Empty Notes",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
