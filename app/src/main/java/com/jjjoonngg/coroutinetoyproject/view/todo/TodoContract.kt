package com.jjjoonngg.coroutinetoyproject.view.todo

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

interface TodoContract {
    interface View {
        fun setAdapter(adapter: TodoRecyclerViewAdapter)

    }

    interface AdapterView {
        var itemListener: TodoItemListener?

        fun initItems(items: List<TodoDTO>?)
        fun notifyAdapter()
        fun updateTodoItem(position: Int)

        interface TodoItemListener {
            fun sendToDoing(position: Int)
        }
    }

    interface Presenter {
        var view: View
        var adapterView: AdapterView
        var model: TodoModel

        fun initialize()
        fun refresh()

        interface TodoActionListener{
            fun pressSendToDoing()
        }
    }
}