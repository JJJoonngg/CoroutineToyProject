package com.jjjoonngg.coroutinetoyproject.view.done

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO

interface DoneContract {

    interface View {
        fun setAdapter(adapter: DoneRecyclerViewAdapter)
    }

    interface AdapterView {
        var itemListener: DoneItemListener?

        fun initItems(items: List<TodoDTO>)
        fun notifyAdapter()
        fun updateTodoItem(position: Int)

        interface DoneItemListener {
            fun sendToDoing(position: Int)
        }
    }

    interface Presenter {
        var view: View
        var adapterView: AdapterView
        var model: TodoModel

        fun initialize()
        fun refresh()

        interface DoneActionListener{
            fun pressSendToDoing()
        }
    }
}