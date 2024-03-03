package com.lytmoon.chatdemo.bean


data class ChatReplyData(
    val choices: List<Choice>?,
    val created: Int?,
    val id: String?,
    val model: String?,
    val `object`: String?,
    val system_fingerprint: String?,
    val usage: Usage?,
    var viewHolder: String?,
    var userAsk: String?
)

data class Choice(
    val finish_reason: String,
    val index: Int,
    val logprobs: Any,
    val message: Message
)

data class Usage(
    val adjust_total: Int,
    val completion_tokens: Int,
    val final_total: Int,
    val pre_token_count: Int,
    val pre_total: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)

data class Message(
    val content: String,
    val role: String
)