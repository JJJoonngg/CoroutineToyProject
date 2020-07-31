package com.jjjoonngg.coroutinetoyproject.view.main

import com.jjjoonngg.coroutinetoyproject.view.doing.DoingContract
import com.jjjoonngg.coroutinetoyproject.view.done.DoneContract
import com.jjjoonngg.coroutinetoyproject.view.todo.TodoContract

interface MainContract {
    interface View {
    }

    interface Presenter {
        var view: View
        var todoPresenter : TodoContract.Presenter
        var doingPresenter: DoingContract.Presenter
        var donePresenter : DoneContract.Presenter
        fun getCurrPageIdx(idx: Int)
        fun clickAddButton()
    }
}