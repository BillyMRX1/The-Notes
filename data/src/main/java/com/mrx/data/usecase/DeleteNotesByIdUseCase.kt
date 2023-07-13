package com.mrx.data.usecase

import com.mrx.data.repository.NotesRepository

class DeleteNotesByIdUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteId: Int) = notesRepository.deleteNote(noteId)
}