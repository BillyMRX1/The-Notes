package com.mrx.data.usecase

import com.mrx.data.model.Notes
import com.mrx.data.repository.NotesRepository

class SaveNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Notes) = notesRepository.saveNote(note)
}