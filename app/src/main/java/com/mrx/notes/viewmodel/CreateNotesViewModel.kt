package com.mrx.notes.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrx.data.extensions.asImmutable
import com.mrx.data.extensions.getValueOrEmpty
import com.mrx.data.extensions.getValueOrZero
import com.mrx.data.model.Notes
import com.mrx.data.usecase.SaveNoteUseCase
import com.mrx.data.usecase.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class CreateNotesViewModel @Inject constructor(
    private val saveNoteUseCase: SaveNoteUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase
) : ViewModel() {

    private val _isNoteValid = MutableLiveData<Boolean>()
    val isNoteValid = _isNoteValid.asImmutable()

    private val _noteId = MutableLiveData<Int>()
    val noteId = _noteId.asImmutable()

    private val _title = mutableStateOf("")
    val title = _title

    private val _createdAt = MutableLiveData<Long>(0)
    val createdAt = _createdAt.asImmutable()

    private val _body = mutableStateOf("")
    val body = _body

    private val _isNoteSaved = MutableLiveData<Boolean>()
    val isNoteSaved = _isNoteSaved.asImmutable()

    init {
        getCurrentTime()
    }

    private fun getCurrentTime() {
        val currentTime = Calendar.getInstance()
        _createdAt.postValue(currentTime.time.time)
    }

    fun setTitle(title: String) {
        _title.value = title
        checkNoteValidation()
    }

    fun setBody(body: String) {
        _body.value = body
        checkNoteValidation()
    }

    private fun checkNoteValidation() {
        _isNoteValid.value = _title.value.isEmpty().not() or _body.value.isEmpty().not()
    }

    fun saveNotes() = viewModelScope.launch {
        val currentTime = Calendar.getInstance()
        val notes = Notes(
            title = _title.value.trim(),
            body = _body.value.trim(),
            createdAt = currentTime.time.time
        )
        _isNoteSaved.postValue(saveNoteUseCase.invoke(notes))
    }

    fun populateDataFromLocal(notes: Notes?) = viewModelScope.launch {
        try {
            _noteId.value = notes?.id
            _title.value = notes?.title.getValueOrEmpty()
            _body.value = notes?.body.getValueOrEmpty()
            _createdAt.value = notes?.createdAt
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateNotes() = viewModelScope.launch {
        val currentTime = Calendar.getInstance()
        val notes = Notes(
            id = _noteId.value.getValueOrZero(),
            title = _title.value.trim(),
            body = _body.value.trim(),
            createdAt = currentTime.time.time
        )
        _isNoteSaved.postValue(updateNotesUseCase.invoke(notes))
    }
}