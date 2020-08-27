package com.example.noteapps.model.implementations


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.example.noteapps.common.Result
import com.example.noteapps.common.awaitTaskCompletable
import com.example.noteapps.model.User
import com.example.noteapps.model.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FirebaseUserRepo(val auth: FirebaseAuth = FirebaseAuth.getInstance()) : IUserRepository {

    override suspend fun signInGoogleUser(idToken: String):
            Result<Exception, Unit> = withContext(Dispatchers.IO) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            awaitTaskCompletable(auth.signInWithCredential(credential))

            Result.build { Unit }
        } catch (exception: Exception) {
            Result.build { throw exception }
        }

    }


    override suspend fun signOutCurrentUser(): Result<Exception, Unit> {
        return Result.build {
            auth.signOut()
        }
    }

    override suspend fun getCurrentUser(): Result<Exception, User?> {
        val firebaseUser = auth.currentUser

        return if (firebaseUser == null) {
            Result.build { null }
        } else {
            Result.build {
                User(
                    firebaseUser.uid,
                    firebaseUser.displayName ?: ""
                )
            }
        }
    }
}