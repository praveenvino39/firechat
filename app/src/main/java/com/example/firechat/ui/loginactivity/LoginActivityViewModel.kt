package com.example.firechat.ui.loginactivity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.firechat.ui.chatactivity.ChatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {
    var email = ObservableField<String>("")
    var password = ObservableField<String>("")
    private val auth = FirebaseAuth.getInstance()

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    fun login() {
        if (email.get().isNullOrBlank()) {
            Toast.makeText(context, "Email is invalid", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.get().isNullOrBlank()) {
            Toast.makeText(context, "Password should'nt be empty", Toast.LENGTH_SHORT).show()
            return
        }
        val authResult =
            auth.signInWithEmailAndPassword(email.get().toString(), password.get().toString())
        authResult.addOnSuccessListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("CURRENT_USER", it.user)
            context.startActivity(intent)

        }
    }
}