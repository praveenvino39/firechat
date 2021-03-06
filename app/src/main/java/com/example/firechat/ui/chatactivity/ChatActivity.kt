package com.example.firechat.ui.chatactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firechat.R
import com.example.firechat.adapter.MessageAdapter
import com.example.firechat.databinding.ActivityChatBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChatActivity : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.extras?.get("CURRENT_USER") as FirebaseUser
        val view = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_chat)
                as ActivityChatBinding
        val viewModel = ViewModelProvider(this).get(ChatActivityViewModel::class.java)
        view.viewModel = viewModel
        viewModel.currentUserEmail = user.email!!
        supportActionBar!!.title = user.email!!
        auth = FirebaseAuth.getInstance()
        auth.addAuthStateListener {
            if(it.currentUser == null){
                finish()
            }
        }
        val sendBtn = findViewById<MaterialButton>(R.id.btnSend)
        val rc_chat = findViewById<RecyclerView>(R.id.rc_message)
        viewModel.messageText.observe(this, {
            sendBtn.isEnabled = !it.trim().equals("")
        })
        viewModel.messagesDocument.observe(this, {
            rc_chat.apply {
                val messageList = it.asReversed()
                val messageAdapter = MessageAdapter(messageList = messageList, viewModel.currentUserEmail)
                val layoutManager = LinearLayoutManager(this@ChatActivity)
                layoutManager.reverseLayout = true
                rc_chat.layoutManager = layoutManager
                rc_chat.adapter = messageAdapter
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout -> {
                auth.signOut()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}