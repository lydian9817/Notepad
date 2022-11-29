package com.example.notepad.presentation.edit

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notepad.domain.model.Note
import com.example.notepad.presentation.home.HomeViewModel
import com.example.notepad.presentation.notes.TextNoteEdit

@Composable
fun EditScreen(
    onClickSaveNote: () -> Unit = {},
    selectedNoteId: String?,
    homeViewModel: HomeViewModel
) {

    val isNoteValid =
        homeViewModel.isNoteValid(homeViewModel.noteTitle, homeViewModel.noteContent)

    Log.i("selected id", "noteId es $selectedNoteId")

    if (selectedNoteId != "noteId" && selectedNoteId != null) {
        Log.i("to int", "es ${selectedNoteId.toInt()}")
        val retrievedNote = homeViewModel.retrieveNote(selectedNoteId.toInt()).observeAsState()
        homeViewModel.updateStates(retrievedNote as MutableState<Note?>)
    }

    Column {
        IconRow()
        TextNoteEdit(
            title = homeViewModel.noteTitle,
            onTitleChange = { homeViewModel.updateNoteTitle(it) },
            content = homeViewModel.noteContent,
            onContentChange = { homeViewModel.updateNoteContent(it) }
        )
        Spacer(modifier = Modifier.height(30.dp))
        SaveButton(onClickSave = onClickSaveNote, isEnabled = isNoteValid)
    }
}

@Composable
fun IconRow() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = "Save button",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text("Edit Screen") //TODO poner la pantalla actual como string
        }
    )
}

@Composable
fun SaveButton(onClickSave: () -> Unit, isEnabled: Boolean) {
    Button(onClick = onClickSave, enabled = isEnabled) {
        Text("Save Note")
    }
}

/*
@Preview
@Composable
fun EditScreenPreview() {
    NotepadTheme() {
        EditScreen()
    }
}
 */