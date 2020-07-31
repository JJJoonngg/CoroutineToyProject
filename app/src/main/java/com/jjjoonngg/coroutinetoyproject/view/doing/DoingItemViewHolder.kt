package com.jjjoonngg.coroutinetoyproject.view.doing

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

class DoingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var sendingToTodo: () -> Unit = {}
    var sendingToDone: () -> Unit = {}
    private val prevButton: ImageButton = view.findViewById(R.id.doingPrevButton)
    private val nextButton: ImageButton = view.findViewById(R.id.doingNextButton)
    private val title: TextView = view.findViewById(R.id.titleContent)
    private val content: TextView = view.findViewById(R.id.contentContent)
    private val date: TextView = view.findViewById(R.id.dateContent)

    fun bind(data: TodoDTO) {
        title.text = data.title
        content.text = data.content
        date.text = data.date
        prevButton.setOnClickListener { sendingToTodo() }
        nextButton.setOnClickListener { sendingToDone() }
    }

}