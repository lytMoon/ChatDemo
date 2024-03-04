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

class ChatViewModel : ViewModel() {

    private val _replyList = MutableLiveData<List<ChatReplyData>>()
    val replyData: LiveData<List<ChatReplyData>>
        get() = _replyList


    private fun addFirstViewHolder(): List<ChatReplyData> {
        val firstHolderList =
            ChatReplyData(null, null, null, null, null, null, null, "firstViewHolder", null)
        _replyList.postValue(listOf(firstHolderList))
        return listOf(firstHolderList)
    }

    fun receiveChatReply(ques: String) {

        getReplyData(ques).subscribe(object : Observer<ChatReplyData> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Log.d("receiveChatReply", "测试数据:${e}")
            }

            override fun onComplete() {
            }

            override fun onNext(t: ChatReplyData) {

                val list = listOf(t)

                for (it in list) {
                    it.viewHolder = "botViewHolder"
                }
                _replyList.value = _replyList.value?.plus(list)

            }

        })
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