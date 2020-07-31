package com.jjjoonngg.coroutinetoyproject.view.doing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

class DoingRecyclerViewAdapter :
    RecyclerView.Adapter<DoingItemViewHolder>(),
    DoingContract.AdapterView {

    override var itemListener: DoingContract.AdapterView.DoingItemListener? = null
    private var dataSet: List<TodoDTO>? = null

    override fun initItems(items: List<TodoDTO>) {
        dataSet = items
    }

    override fun getItemCount(): Int = dataSet?.size ?: 0

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun updateTodoItem(position: Int) {
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DoingItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_item_doing, parent, false)
    )

    override fun onBindViewHolder(holder: DoingItemViewHolder, position: Int) {
        with(holder) {
            dataSet?.let {
                bind(it[position])
                sendingToTodo = { itemListener?.sendingToTodo(position) }
                sendingToDone = { itemListener?.sendingToDone(position) }
            }
        }
    }

}