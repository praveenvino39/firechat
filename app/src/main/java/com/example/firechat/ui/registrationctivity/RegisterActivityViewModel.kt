package com.example.firechat.ui.registrationctivity

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.example.firechat.ui.loginactivity.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivityViewModel(application: Application) : AndroidViewModel(application) {
    var email = ObservableField<String>("")
    var password = ObservableField<String>("")
    private val auth = FirebaseAuth.getInstance()
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext



    fun register() {
        if (email.get().isNullOrBlank()) {
            Toast.makeText(context, "Email is invalid", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.get().isNullOrBlank()) {
            Toast.makeText(context, "Password should'nt be empty", Toast.LENGTH_SHORT).show()
            return
        }
        val authResult =
            auth.createUserWithEmailAndPassword(email.get().toString(), password.get().toString())
        authResult.addOnSuccessListener {
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    fun gotoLogin(){
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}