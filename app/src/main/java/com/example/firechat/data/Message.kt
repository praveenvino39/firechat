package com.example.firechat.data

import com.google.firebase.Timestamp
import java.io.Serializable

data class Message(var message:String, var sender:String, var sentAt:Timestamp):Serializable
