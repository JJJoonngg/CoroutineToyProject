package com.jjjoonngg.coroutinetoyproject.view.done

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.data.db.TodoDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DonePresenter : DoneContract.Presenter {

    override lateinit var view: DoneContract.View
    override lateinit var adapterView: DoneContract.AdapterView
    override lateinit var model: TodoModel
    lateinit var datas: MutableList<TodoDTO>

    var doneActionListener: DoneContract.Presenter.DoneActionListener? = null

    private val doneListItemListener =
        object : DoneContract.AdapterView.DoneItemListener {
            override fun sendToDoing(position: Int) {
                val currentItem = datas[position]
                GlobalScope.launch(Dispatchers.Main) {
                    TodoModel.sendToDoingFromDone(currentItem)
                    doneActionListener?.pressSendToDoing()
                }
                datas.removeAt(position)
                adapterView.notifyAdapter()
            }
        }

    override fun initialize() {
        GlobalScope.launch(Dispatchers.Main) {
            DoneRecyclerViewAdapter().let { adapter ->
                adapterView = adapter
                adapterView.itemListener = doneListItemListener
                view.setAdapter(adapter)
            }
            GlobalScope.launch(Dispatchers.Main) {
                datas = TodoModel.readDoneFromFireStore()!!
            }.join()
            adapterView.initItems(datas)
            adapterView.notifyAdapter()
        }
    }

    override fun refresh() {
        initialize()
    }
}