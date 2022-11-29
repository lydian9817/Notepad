package com.example.notepad.presentation.edit

import androidx.lifecycle.ViewModel
import com.example.notepad.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

}