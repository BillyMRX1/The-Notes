package com.mrx.data.datasource

import com.mrx.data.db.NotesDao
import com.mrx.data.model.Notes

class NotesLocalDataSourceImpl(
    private val notesDao: NotesDao
) : NotesLocalDataSource {
    override suspend fun getAllNotes(): List<Notes> = notesDao.getAllNotes()

    override suspend fun saveNote(note: Notes): Boolean {
        return try {
            notesDao.createNote(note)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun deleteNote(noteId: Int): Boolean {
        return try {
            notesDao.deleteNoteById(noteId)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateNote(note: Notes): Boolean {
        return try {
            notesDao.updateNote(note)
            true
        } catch (e: Exception) {
            false
        }
    }
}