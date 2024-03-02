package com.lytmoon.chatdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lytmoon.chatdemo.bean.ChatReplyData
import com.lytmoon.chatdemo.repository.ChatNetWork
import com.lytmoon.chatdemo.repository.ChatNetWork.getReplyData

class ChatViewModel : ViewModel() {

    private val _replyList = MutableLiveData<List<ChatReplyData>>()
    val replyData: LiveData<List<ChatReplyData>>
        get() = _replyList

    fun receiveChatReply(ques:String){
        _replyList.postValue(getReplyData(ques))
    }

}