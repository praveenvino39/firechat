package com.example.firechat.ui.loginactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.firechat.R
import com.example.firechat.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_login)
                as ActivityLoginBinding
        val viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)
        view.viewModel = viewModel
    }
}