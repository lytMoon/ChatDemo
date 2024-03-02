import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            return oldItem.answer == newItem.answer
        }
    }) {


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            //这里相当于当item为0的时候为轮播图的viewHolder做了个标记
            0 -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return when (viewType) {
            0 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_rv_top, parent, false)

                RvNewsViewHolder(view)
            }

            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_rv_top, parent, false)

                RvNewsViewHolder(view)
            }
        }

        //轮播图


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is RvNewsTopViewHolder -> {}

            is RvNewsViewHolder -> {
                val itemData = getItem(position)
                holder.bind(itemData)
            }
        }
    }


    sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class RvNewsTopViewHolder(itemView: View) : BaseViewHolder(itemView) {

    }

    inner class RvNewsViewHolder(itemView: View) : BaseViewHolder(itemView) {

        fun bind(itemData: ChatReplyData) {

        }

    }
}

