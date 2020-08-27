package com.example.noteapps.login.buildlogic

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.noteapps.model.implementations.FirebaseUserRepo
import com.example.noteapps.model.repository.IUserRepository
import com.google.firebase.FirebaseApp

class LoginInjector(application: Application): AndroidViewModel(application) {

    init {
        FirebaseApp.initializeApp(application)
    }

    private fun getUserRepository(): IUserRepository {
        return FirebaseUserRepo()
    }

    fun provideUserViewModelFactory(): UserViewModelFactory =
        UserViewModelFactory(
            getUserRepository()
        )

}