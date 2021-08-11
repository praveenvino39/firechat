package com.example.firechat.ui.registrationctivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.example.firechat.R
import com.example.firechat.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_register)
                as ActivityRegisterBinding
        val viewModel = ViewModelProvider(this).get(RegisterActivityViewModel::class.java)
        view.viewModel = viewModel
    }
}