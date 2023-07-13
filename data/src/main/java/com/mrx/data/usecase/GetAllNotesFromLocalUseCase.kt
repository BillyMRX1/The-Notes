package com.mrx.data.usecase

import com.mrx.data.repository.NotesRepository

class GetAllNotesFromLocalUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke() = notesRepository.getAllNotes()
}