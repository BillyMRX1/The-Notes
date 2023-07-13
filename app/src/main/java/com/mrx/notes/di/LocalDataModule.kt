package com.mrx.notes.di

import com.mrx.data.datasource.NotesLocalDataSource
import com.mrx.data.datasource.NotesLocalDataSourceImpl
import com.mrx.data.db.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(notesDao: NotesDao): NotesLocalDataSource =
        NotesLocalDataSourceImpl(notesDao)
}