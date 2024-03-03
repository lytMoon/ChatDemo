package com.lytmoon.chatdemo.repository

import com.lytmoon.chatdemo.api.ChatApiService
import com.lytmoon.chatdemo.bean.ChatReplyData
import com.lytmoon.chatdemo.bean.ChatRequest
import com.lytmoon.chatdemo.bean.Message
import com.lytmoon.chatdemo.bean.MessageSend
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ChatNetWork {

    private val url = "https://oa.api2d.net/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()


    private val chatApi = retrofit.create(ChatApiService::class.java)


    fun getReplyData(ques: String): Observable<ChatReplyData> {

        val message = MessageSend("user", ques)
        val request = ChatRequest("gpt-3.5-turbo", listOf(message), false)

        return chatApi.getChatCompletion(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}