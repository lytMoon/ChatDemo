package com.lytmoon.chatdemo.bean

data class ChatRequest(
    val model: String,
    val messages: List<MessageSend>,
    val safe_mode: Boolean
)

data class MessageSend(
    val role: String,
    val content: String
)