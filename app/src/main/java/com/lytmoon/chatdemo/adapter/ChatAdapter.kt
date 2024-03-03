import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lytmoon.chatdemo.R
import com.lytmoon.chatdemo.bean.ChatReplyData

class ChatAdapter() :
    ListAdapter<ChatReplyData, ViewHolder>(object : DiffUtil.ItemCallback<ChatReplyData>() {
        override fun areItemsTheSame(oldItem: ChatReplyData, newItem: ChatReplyData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ChatReplyData, newItem: ChatReplyData): Boolean {
            return oldItem.id == newItem.id
        }
    }) {


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {


        return when (currentList[position].viewHolder) {
            "firstViewHolder" -> {
                0
            }

            "botViewHolder" -> {
                1
            }

            "userViewHolder" -> {
                2
            }

            else -> {
                3
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_rv_top, parent, false)

                TopViewHolder(view)
            }

            1 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rv_chat_bot, parent, false)

                BotViewHolder(view)
            }

            2 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rv_chat_user, parent, false)

                UserViewHolder(view)
            }


            else -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_rv_other, parent, false)

                BotViewHolder(view)
            }
        }

        //轮播图


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is TopViewHolder -> {}

            is BotViewHolder -> {
                val itemData = getItem(position)
                holder.bind(itemData)
            }

            is UserViewHolder -> {
                val itemData = getItem(position)
                holder.bind(itemData)
            }
        }
    }


    sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TopViewHolder(itemView: View) : BaseViewHolder(itemView) {

    }

    inner class BotViewHolder(itemView: View) : BaseViewHolder(itemView) {

        private val mBot: TextView = itemView.findViewById(R.id.chat_bot_reply)

        fun bind(itemData: ChatReplyData) {
            mBot.text = itemData.choices?.get(0)?.message?.content ?: "信息返回失败，请稍等"
        }

    }

    inner class UserViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val mUserQuest: TextView = itemView.findViewById(R.id.chat_user_ask)

        fun bind(itemData: ChatReplyData) {
            mUserQuest.text = itemData.userAsk
        }


    }
}

