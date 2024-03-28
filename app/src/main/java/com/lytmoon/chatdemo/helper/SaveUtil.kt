package com.lytmoon.chatdemo.helper

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lytmoon.chatdemo.bean.ChatReplyData

object SaveUtil {

    private val gson = Gson()
    private val firstHolderList =
        ChatReplyData(null, null, null, null, null, null, null, "firstViewHolder", null)

    fun saveChatReplyList(context: Context, chatReplyList: List<ChatReplyData>) {
        val sharedPreferences =
            context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val json = gson.toJson(chatReplyList)

        // 将 JSON 字符串保存到 SharedPreferences 中
        sharedPreferences.edit().putString("chatReplyList", json).apply()
    }

    // 定义一个函数来从 SharedPreferences 中获取保存的 List<ChatReplyData> 对象
    fun getChatReplyList(context: Context): List<ChatReplyData> {
        val sharedPreferences =
            context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString("chatReplyList", null)
        val type = object : TypeToken<List<ChatReplyData>>() {}.type
        return gson.fromJson(json, type) ?: listOf(firstHolderList)
    }
}
