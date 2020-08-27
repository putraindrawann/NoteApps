package com.example.noteapps.notedetail.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.noteapps.model.RoomNoteDatabase
import com.example.noteapps.model.implementations.NoteRepoImpl
import com.example.noteapps.model.repository.INoteRepository
import com.google.firebase.FirebaseApp

class NoteDetailInjector(application: Application): AndroidViewModel(application) {

    private fun getNoteRepository(): INoteRepository {
        FirebaseApp.initializeApp(getApplication())
        return NoteRepoImpl(
            local = RoomNoteDatabase.getInstance(getApplication()).roomNoteDao()
        )
    }

    fun provideNoteViewModelFactory(): NoteViewModelFactory =
        NoteViewModelFactory(
            getNoteRepository()
        )

}