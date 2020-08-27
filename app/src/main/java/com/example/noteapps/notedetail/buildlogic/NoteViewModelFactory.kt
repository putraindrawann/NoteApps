package com.example.noteapps.notedetail.buildlogic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapps.model.repository.INoteRepository
import com.example.noteapps.NoteViewModel
import kotlinx.coroutines.Dispatchers

class NoteViewModelFactory(
    private val noteRepo: INoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return NoteViewModel(noteRepo, Dispatchers.Main) as T
    }

}