package com.mrx.notes.di

import android.content.Context
import com.mrx.data.db.NotesDB
import com.mrx.data.db.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): NotesDB =
        NotesDB.getInstance(appContext)

    @Provides
    fun provideNotesDao(notesDB: NotesDB): NotesDao = notesDB.notesDao()
}