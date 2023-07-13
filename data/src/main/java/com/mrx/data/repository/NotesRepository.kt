package com.mrx.data.repository

import com.mrx.data.model.Notes

interface NotesRepository {
    suspend fun getAllNotes(): List<Notes>

    suspend fun saveNote(note: Notes): Boolean

    suspend fun deleteNote(noteId: Int): Boolean

    suspend fun updateNote(note: Notes): Boolean
}