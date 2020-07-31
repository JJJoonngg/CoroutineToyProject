package com.jjjoonngg.coroutinetoyproject.view.main

import android.util.Log
import android.view.View
import com.jjjoonngg.coroutinetoyproject.util.IDX_DOING_TAB
import com.jjjoonngg.coroutinetoyproject.util.IDX_TODO_TAB
import com.jjjoonngg.coroutinetoyproject.util.TAG
import com.jjjoonngg.coroutinetoyproject.util.ui.CustomFloatingActionButtonUI
import com.jjjoonngg.coroutinetoyproject.view.doing.DoingContract
import com.jjjoonngg.coroutinetoyproject.view.done.DoneContract
import com.jjjoonngg.coroutinetoyproject.view.todo.TodoContract

class MainPresenter : MainContract.Presenter {
    override lateinit var view: MainContract.View

    override lateinit var todoPresenter: TodoContract.Presenter
    override lateinit var doingPresenter: DoingContract.Presenter
    override lateinit var donePresenter: DoneContract.Presenter

    val todoListener = object : TodoContract.Presenter.TodoActionListener {
        override fun pressSendToDoing() {
            doingPresenter.refresh()
        }
    }

    val doingListener = object : DoingContract.Presenter.DoingActionListener {
        override fun pressSendToTodo() {
            todoPresenter.refresh()
        }

        override fun pressSendToDone() {
            donePresenter.refresh()
        }
    }

    val doneListener = object : DoneContract.Presenter.DoneActionListener {
        override fun pressSendToDoing() {
            doingPresenter.refresh()
        }
    }

    override fun getCurrPageIdx(idx: Int) {
        when (idx) {
            IDX_TODO_TAB -> {
                with(todoPresenter) {
                }

            }
            IDX_DOING_TAB -> {
                with(doingPresenter) {
                }
            }
            else -> {
                with(donePresenter) {
                }
            }
        }
    }

    override fun clickAddButton() {
        todoPresenter.refresh()
    }

}