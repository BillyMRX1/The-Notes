package com.mrx.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrx.data.extensions.asImmutable
import com.mrx.data.model.Notes
import com.mrx.data.usecase.DeleteNotesByIdUseCase
import com.mrx.data.usecase.GetAllNotesFromLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllNotesFromLocalUseCase: GetAllNotesFromLocalUseCase,
    private val deleteNotesByIdUseCase: DeleteNotesByIdUseCase
) : ViewModel() {

    private val _allNotes = MutableLiveData<List<Notes>>()
    val allNotes = _allNotes.asImmutable()

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog = _showDialog.asImmutable()

    private val _noteId = MutableLiveData<Int>()
    val noteId = _noteId.asImmutable()

    fun getAllNotes() = viewModelScope.launch {
        try {
            _allNotes.postValue(getAllNotesFromLocalUseCase.invoke())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteNotes(noteId: Int) = viewModelScope.launch {
        try {
            deleteNotesByIdUseCase.invoke(noteId)
            _allNotes.postValue(getAllNotesFromLocalUseCase.invoke())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showAlertDialog() {
        _showDialog.value = true
    }

    fun dismissAlertDialog() {
        _noteId.postValue(null)
        _showDialog.value = false
    }

    fun setNoteId(noteId: Int) {
        _noteId.postValue(noteId)
    }
}