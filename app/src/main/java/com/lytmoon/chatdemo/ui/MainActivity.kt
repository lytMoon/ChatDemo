package com.lytmoon.chatdemo.ui

import ChatAdapter
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lytmoon.chatdemo.R
import com.lytmoon.chatdemo.helper.SaveUtil
import com.lytmoon.chatdemo.viewmodel.ChatViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mRv: RecyclerView
    private lateinit var mEdit: androidx.appcompat.widget.SearchView
    private lateinit var mSend: ImageView
    private lateinit var mClear: ImageView
    private lateinit var mBottomLayout: FrameLayout


    private val mViewModel by lazy {
        ViewModelProvider(this)[ChatViewModel::class.java]
    }
    private val mReplyAdapter: ChatAdapter by lazy { ChatAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniView()
        iniTrans()
        iniListener()
        iniReply()
        iniBeforeData()

    }

    private fun iniBeforeData() {
        mViewModel.readFromLocal(this@MainActivity)
    }

    private fun iniListener() {
        mSend.setOnClickListener {
            if (mEdit.query.isNotEmpty()) {
                mViewModel.addUserQuest(mEdit.query.toString())
                mViewModel.receiveChatReply(mEdit.query.toString())
                mEdit.clearFocus()
                mEdit.setQuery("", false)
            } else {
                Toast.makeText(this, "请输入问题", Toast.LENGTH_SHORT).show()
            }
        }

        val rootView = findViewById<View>(android.R.id.content)
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keyboardHeight = screenHeight - rect.bottom
            val isKeyboardVisible = keyboardHeight > screenHeight * 0.15
            if (isKeyboardVisible) {
                mBottomLayout.translationY = -keyboardHeight.toFloat()
            } else {
                mBottomLayout.translationY = 0f
            }
        }
        mClear.setOnClickListener {
            mViewModel.clearChatData()
        }


    }

    private fun iniView() {
        mRv = findViewById(R.id.rv_chat)
        mSend = findViewById(R.id.iv_user_send)
        mEdit = findViewById<androidx.appcompat.widget.SearchView?>(R.id.sv_user_input).apply {
            findViewById<EditText>(androidx.appcompat.R.id.search_src_text).apply {
                textSize = 16F
                (this.layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(28, 0, 0, 0)
                }
                background = null
            }
            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).apply {
                setImageDrawable(null)
            }
            queryHint = "点击输入问题"
            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query.isNotEmpty()) {
                        mViewModel.addUserQuest(query)
                        mViewModel.receiveChatReply(query)
                        mEdit.clearFocus()
                        mEdit.setQuery("", false)
                    } else {
                        Toast.makeText(this@MainActivity, "问题不能为空", Toast.LENGTH_SHORT).show()
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
            setBackgroundColor(Color.TRANSPARENT)
        }

        mBottomLayout = findViewById(R.id.fl_bottom)
        mClear = findViewById(R.id.iv_clear)


    }

    private fun iniTrans() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun iniReply() {

        mViewModel.replyData.observe(this@MainActivity) {
            mReplyAdapter.submitList(it)
            SaveUtil.saveChatReplyList(this, it)
            mRv.scrollToPosition(it.size-1)
        }
        mRv.layoutManager = LinearLayoutManager(this)
        mRv.adapter = mReplyAdapter


    }
}