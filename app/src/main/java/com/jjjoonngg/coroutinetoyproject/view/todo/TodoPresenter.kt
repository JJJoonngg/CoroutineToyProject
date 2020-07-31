package com.jjjoonngg.coroutinetoyproject.view.todo

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoPresenter : TodoContract.Presenter {

    override lateinit var view: TodoContract.View
    override lateinit var adapterView: TodoContract.AdapterView
    override lateinit var model: TodoModel
    lateinit var datas: MutableList<TodoDTO>

    var todoActionListener: TodoContract.Presenter.TodoActionListener? = null

    private val todoListItemListener =
        object : TodoContract.AdapterView.TodoItemListener {
            override fun sendToDoing(position: Int) {
                val currentItem = datas[position]
                GlobalScope.launch(Dispatchers.Main) {
                    TodoModel.sendToDoingFromTodo(currentItem)
                    todoActionListener?.pressSendToDoing()
                }
                datas.removeAt(position)
                adapterView.notifyAdapter()
            }
        }

    override fun initialize() {
        GlobalScope.launch(Dispatchers.Main) {
            TodoRecyclerViewAdapter().let { adapter ->
                adapterView = adapter
                adapterView.itemListener = todoListItemListener
                view.setAdapter(adapter)
            }
            GlobalScope.launch(Dispatchers.Main) {
                datas = TodoModel.readTodoFromFireStore()!!
            }.join()
            adapterView.initItems(datas)
            adapterView.notifyAdapter()
        }
    }

    override fun refresh() {
        initialize()
    }
}