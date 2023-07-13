package com.mrx.notes.di

import com.mrx.data.repository.NotesRepository
import com.mrx.data.usecase.DeleteNotesByIdUseCase
import com.mrx.data.usecase.GetAllNotesFromLocalUseCase
import com.mrx.data.usecase.SaveNoteUseCase
import com.mrx.data.usecase.UpdateNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideAllLocalNotesUseCase(notesRepository: NotesRepository) =
        GetAllNotesFromLocalUseCase(notesRepository)

    @Provides
    fun provideSaveNoteUseCase(notesRepository: NotesRepository) =
        SaveNoteUseCase(notesRepository)

    @Provides
    fun provideDeleteNoteByIdUseCase(notesRepository: NotesRepository) =
        DeleteNotesByIdUseCase(notesRepository)

    @Provides
    fun provideUpdateNoteUseCase(notesRepository: NotesRepository) =
        UpdateNotesUseCase(notesRepository)
}