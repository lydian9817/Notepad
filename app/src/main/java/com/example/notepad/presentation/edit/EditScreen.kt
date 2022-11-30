package com.example.notepad.presentation.edit

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notepad.presentation.edit.components.HintTextField
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditScreen(
    onClickSaveNote: () -> Unit = {},
    viewModel: EditViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    //This only executes once, not at recompositions
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is EditViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is EditViewModel.UiEvent.SaveNote -> {
                    onClickSaveNote()
                }
            }

        }
    }

    Scaffold(
        topBar = { AppBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(EditNoteEvent.SaveNote)
                }
            ) {
                Icon(imageVector = Icons.Rounded.Done, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            HintTextField(
                text = contentState.text,
                hint = contentState.hint,
                onValueChange = {
                    viewModel.onEvent(EditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(EditNoteEvent.ChangeContentFocus(it))
                },
                isHintVisible = contentState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}


@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Rounded.Done,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        },
        title = {
            Text("Edit Screen") //TODO poner la pantalla actual como string
        }
    )
}
