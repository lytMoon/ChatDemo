package com.lytmoon.chatdemo.ui

import ChatAdapter
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytmoon.chatdemo.R
import com.lytmoon.chatdemo.databinding.ActivityMainBinding
import com.lytmoon.chatdemo.viewmodel.ChatViewModel

class MainActivity : AppCompatActivity() {

    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
    private lateinit var mRv: RecyclerView

    private val mViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }
    private val mReplyAdapter: ChatAdapter by lazy { ChatAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniView()
        iniTrans()
        iniReply()


    }

    private fun iniView() {
        mRv = findViewById(R.id.rv_chat)

    }

    private fun iniTrans() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun iniReply() {
        mViewModel.receiveChatReply("")

        mViewModel.replyData.observe(this@MainActivity) {
            mReplyAdapter.submitList(it)
            Log.d("65656565656", "测试数据:${mReplyAdapter.currentList}")
            Log.d("LogTest1212", "测试数据:${it}")
        }
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mReplyAdapter
//        mBinding.rvChat.layoutManager = LinearLayoutManager(this)
//        mBinding.rvChat.adapter = mReplyAdapter


    }
}