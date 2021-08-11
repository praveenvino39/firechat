package com.example.firechat.ui.chatactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.firechat.data.Message
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class ChatActivityViewModel(application: Application):AndroidViewModel(application) {
    lateinit var currentUserEmail:String
    var messageText = MutableLiveData<String>("")
    val firestore = FirebaseFirestore.getInstance()
    val sendIndicator = MutableLiveData<String>()
    val messagesDocument = MutableLiveData<List<DocumentSnapshot>>()


    init {
        firestore.collection("messages").addSnapshotListener { value, _ ->
            run {
                value?.let {
                    messagesDocument.value = value.documents
                }
            }
        }
    }


    fun sendMessage(){
        val reference = firestore.collection("messages")
        val message = Message(messageText.value.toString(), currentUserEmail,sentAt =  Timestamp.now() )
        reference.document(Timestamp.now().toString()).set(message)
        sendIndicator.value = "sended"
    }
}