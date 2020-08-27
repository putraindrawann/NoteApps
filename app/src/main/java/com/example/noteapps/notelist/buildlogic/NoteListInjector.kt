package com.example.noteapps.notelist.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.noteapps.model.RoomNoteDatabase
import com.example.noteapps.model.implementations.NoteRepoImpl
import com.example.noteapps.model.repository.INoteRepository
import com.google.firebase.FirebaseApp

class NoteListInjector(application:Application): AndroidViewModel(application) {
    private fun getNoteRepository(): INoteRepository {
        FirebaseApp.initializeApp(getApplication())
        return NoteRepoImpl(
            local = RoomNoteDatabase.getInstance(getApplication()).roomNoteDao()
        )
    }

    fun provideNoteListViewModelFactory(): NoteListViewModelFactory =
        NoteListViewModelFactory(
            getNoteRepository()
        )
}