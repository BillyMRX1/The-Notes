package com.mrx.data.repository

import com.mrx.data.datasource.NotesLocalDataSource
import com.mrx.data.model.Notes

class NotesRepositoryImpl(
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {
    override suspend fun getAllNotes(): List<Notes> = notesLocalDataSource.getAllNotes()

    override suspend fun saveNote(note: Notes): Boolean = notesLocalDataSource.saveNote(note)

    override suspend fun deleteNote(noteId: Int): Boolean = notesLocalDataSource.deleteNote(noteId)

    override suspend fun updateNote(note: Notes): Boolean = notesLocalDataSource.updateNote(note)
}