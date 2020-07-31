package com.jjjoonngg.coroutinetoyproject.view.doing

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DoingPresenter : DoingContract.Presenter {

    override lateinit var view: DoingContract.View
    override lateinit var adapterView: DoingContract.AdapterView
    override lateinit var model: TodoModel
    lateinit var datas: MutableList<TodoDTO>

    var doingActionListener: DoingContract.Presenter.DoingActionListener? = null

    private val doingListItemListener =
        object : DoingContract.AdapterView.DoingItemListener {

            override fun sendingToTodo(position: Int) {
                val currentItem = datas[position]
                GlobalScope.launch(Dispatchers.Main) {
                    TodoModel.sendToTodoFromDoing(currentItem)
                    doingActionListener?.pressSendToTodo()
                }
                datas.removeAt(position)
                adapterView.notifyAdapter()
            }

            override fun sendingToDone(position: Int) {
                val currentItem = datas[position]
                GlobalScope.launch(Dispatchers.Main) {
                    TodoModel.sendToDoneFromDoing(currentItem)
                    doingActionListener?.pressSendToDone()
                }
                datas.removeAt(position)
                adapterView.notifyAdapter()
            }
        }

    override fun initialize() {
        GlobalScope.launch(Dispatchers.Main) {
            DoingRecyclerViewAdapter().let { adapter ->
                adapterView = adapter
                adapterView.itemListener = doingListItemListener
                view.setAdapter(adapter)
            }
            GlobalScope.launch(Dispatchers.Main) {
                datas = TodoModel.readDoingFromFireStore()!!
            }.join()
            adapterView.initItems(datas)
            adapterView.notifyAdapter()
        }
    }

    override fun refresh() {
        initialize()
    }
}