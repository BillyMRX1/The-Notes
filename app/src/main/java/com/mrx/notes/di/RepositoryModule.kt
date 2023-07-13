package com.mrx.notes.di

import com.mrx.data.datasource.NotesLocalDataSource
import com.mrx.data.repository.NotesRepository
import com.mrx.data.repository.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideRepositoryModule(
        notesLocalDataSource: NotesLocalDataSource
    ): NotesRepository = NotesRepositoryImpl(notesLocalDataSource)
}