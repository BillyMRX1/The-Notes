package com.mrx.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mrx.data.model.Notes

@Database(
    entities = [Notes::class],
    version = 1,
    exportSchema = false
)
abstract class NotesDB : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getInstance(context: Context): NotesDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): NotesDB =
            Room.databaseBuilder(
                context.applicationContext,
                NotesDB::class.java,
                "notes_db"
            ).build()
    }
}