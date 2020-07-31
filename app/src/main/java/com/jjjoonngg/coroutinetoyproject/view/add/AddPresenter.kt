package com.jjjoonngg.coroutinetoyproject.view.add

import com.jjjoonngg.coroutinetoyproject.data.db.TodoModel
import com.jjjoonngg.coroutinetoyproject.util.TODO

class AddPresenter : AddContract.Presenter {

    override lateinit var view: AddContract.View

    override fun sendData(datas: List<String>) {
        val title = datas[0]
        val content = datas[1]
        val date = datas[2]
        val type: String = TODO
        TodoModel.insertFireStore(title, content, date, type)
    }
}