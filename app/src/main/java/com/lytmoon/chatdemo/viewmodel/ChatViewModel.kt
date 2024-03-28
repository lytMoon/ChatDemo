package com.lytmoon.chatdemo.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lytmoon.chatdemo.bean.ChatReplyData
import com.lytmoon.chatdemo.helper.SaveUtil
import com.lytmoon.chatdemo.repository.ChatNetWork.getReplyData
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ChatViewModel : ViewModel() {

    private val _replyList = MutableLiveData<List<ChatReplyData>>()
    val replyData: LiveData<List<ChatReplyData>>
        get() = _replyList


    fun receiveChatReply(ques: String) {

        getReplyData(ques).map {
            it.viewHolder = "botViewHolder"
            it
        }.subscribe(object : Observer<ChatReplyData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
            }

            override fun onComplete() {
            }

            override fun onNext(t: ChatReplyData) {
                _replyList.value = _replyList.value?.plus(t)
            }
        })
    }


    private fun addFirstViewHolder(): List<ChatReplyData> {
        val firstHolderList =
            ChatReplyData(null, null, null, null, null, null, null, "firstViewHolder", null)
        _replyList.postValue(listOf(firstHolderList))
        return listOf(firstHolderList)
    }


    fun readFromLocal(context: Context) {
        val list = SaveUtil.getChatReplyList(context)
        _replyList.postValue(list)
    }

    fun addUserQuest(ask: String) {
        val userViewHolder =
            ChatReplyData(null, null, null, null, null, null, null, "userViewHolder", ask)
        _replyList.value = _replyList.value?.plus(userViewHolder)
    }

    fun clearChatData() {
        _replyList.value = addFirstViewHolder()
    }

}