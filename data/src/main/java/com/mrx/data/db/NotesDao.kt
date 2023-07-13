package com.mrx.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mrx.data.model.Notes

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<Notes>

    @Update
    suspend fun updateNote(note: Notes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(note: Notes)

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int)
}