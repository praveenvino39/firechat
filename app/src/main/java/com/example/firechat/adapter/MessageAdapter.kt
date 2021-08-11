package com.example.firechat.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.firechat.R
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat


class MessageAdapter(var messageList: List<DocumentSnapshot>, var currentUser: String) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.chat_bubbles, parent, false)
        return ViewHolder(itemView = item)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chatTextView = holder.item.findViewById<TextView>(R.id.message_content)
        val messageContainer = holder.item.findViewById<LinearLayout>(R.id.message_container)
        val messageStyleElement = holder.item.findViewById<CardView>(R.id.style)
        val timestamp = holder.item.findViewById<TextView>(R.id.timestamp)
        timestamp.setTextColor(Color.GRAY)
        if (messageList[position].data!!.get("sender") == currentUser) {
            messageContainer.gravity = Gravity.END
            messageStyleElement.setCardBackgroundColor(Color.parseColor("#BEFF7B17"))
            chatTextView.setTextColor(Color.WHITE)
            chatTextView.text = messageList[position].data!!
                .get("message").toString()
            var sendAt = messageList[position].data!!
                .get("sentAt") as Timestamp
            var date = sendAt.toDate()
            val format: DateFormat = SimpleDateFormat("H:mm:a")
            timestamp.text = format.format(date)
        } else {
            chatTextView.text = messageList[position].data!!
                .get("message").toString()
            var sendAt = messageList[position].data!!
                .get("sentAt") as Timestamp
            var date = sendAt.toDate()
            val format: DateFormat = SimpleDateFormat("H:mm:a")
            timestamp.text = format.format(date)
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}