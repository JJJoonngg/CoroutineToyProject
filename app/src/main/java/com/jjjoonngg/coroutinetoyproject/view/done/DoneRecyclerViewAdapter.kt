package com.jjjoonngg.coroutinetoyproject.view.done

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

class DoneRecyclerViewAdapter :
    RecyclerView.Adapter<DoneItemViewHolder>(),
    DoneContract.AdapterView {

    override var itemListener: DoneContract.AdapterView.DoneItemListener? = null
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DoneItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_item_done, parent, false)
    )


    override fun onBindViewHolder(holder: DoneItemViewHolder, position: Int) {
        with(holder) {
            dataSet?.let {
                bind(it[position])
                sendingToDoing = { itemListener?.sendToDoing(position) }
            }
        }
    }
}
