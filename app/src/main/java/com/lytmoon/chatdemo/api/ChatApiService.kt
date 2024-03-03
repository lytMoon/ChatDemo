package com.lytmoon.chatdemo.api

import com.lytmoon.chatdemo.bean.ChatReplyData
import com.lytmoon.chatdemo.bean.ChatRequest
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatApiService {


    @Headers(
        "Content-Type: application/json",
        "Authorization:Bear fk224158-V4FCmguObF5ciFRBnYXxGXk6vADNnK4Q"
    )
    @POST("v1/chat/completions")
    fun getChatCompletion(@Body body: ChatRequest): Observable<ChatReplyData>


}