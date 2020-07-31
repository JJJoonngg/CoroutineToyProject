package com.jjjoonngg.coroutinetoyproject.view.doing

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

interface DoingContract {

    interface View {
        fun setAdapter(adapter: DoingRecyclerViewAdapter)
    }

    interface AdapterView {
        var itemListener: DoingItemListener?

        fun initItems(items: List<TodoDTO>)
        fun notifyAdapter()
        fun updateTodoItem(position: Int)

        interface DoingItemListener {
            fun sendingToTodo(position: Int)
            fun sendingToDone(position: Int)
        }
    }

    interface Presenter {
        var view: View
        var adapterView: AdapterView
        var model: TodoModel

        fun initialize()
        fun refresh()

        interface DoingActionListener{
            fun pressSendToTodo()
            fun pressSendToDone()
        }
    }
}