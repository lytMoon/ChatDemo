package com.lytmoon.chatdemo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.lytmoon.chatdemo.bean.ChatReplyData


object ChatNetWork {


    fun getReplyData(ques: String): List<ChatReplyData>? {

        val list = mutableListOf<ChatReplyData>()
        for (it in 0 until 10) {
           list.add(it,ChatReplyData(it.toString()))
        }
        Log.d("LogTest","测试数据:${list}")
        return list


//        val proxy = Proxys.http("127.0.0.1", 7890)
//        val chatGPT = ChatGPT.builder()
//            .apiKey(ApiKey.str)
//            .proxy(proxy)
//            .timeout(900)
//            .apiHost("https://api.openai.com/") //反向代理地址
//            .build()
//            .init()
//
//        val system = Message.ofSystem("你是由lytMoon开发的chatGPT聊天语言模型")
//        val message: Message = Message.of(ques)
//
//        val chatCompletion = ChatCompletion.builder()
//            .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//            .maxTokens(4000)
//            .messages(listOf(system, message))
//            .temperature(0.9)
//            .build()
//        val response = chatGPT.chatCompletion(chatCompletion)
//        Log.d("LogTest", "测试数据:${response}")
//        val res: Message = response.choices[0].message
//        return res.content

    }
}