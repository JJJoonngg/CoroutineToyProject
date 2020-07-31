package com.jjjoonngg.coroutinetoyproject.view.main

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jjjoonngg.coroutinetoyproject.util.IDX_DOING_TAB
import com.jjjoonngg.coroutinetoyproject.util.IDX_DONE_TAB
import com.jjjoonngg.coroutinetoyproject.util.IDX_TODO_TAB
import com.jjjoonngg.coroutinetoyproject.view.doing.DoingFragment
import com.jjjoonngg.coroutinetoyproject.view.done.DoneFragment
import com.jjjoonngg.coroutinetoyproject.view.todo.TodoFragment

class MainAdapter(
    private val mainPresenter: MainPresenter,
    fragmentManager: FragmentManager
) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragmentCount = 3

    private val todoTab = TodoFragment().apply {
        presenter.todoActionListener = mainPresenter.todoListener
        mainPresenter.todoPresenter = this.presenter
    }

    private val doingTab = DoingFragment().apply {
        presenter.doingActionListener = mainPresenter.doingListener
        mainPresenter.doingPresenter = this.presenter
    }

    private val doneTab = DoneFragment().apply {
        presenter.doneActionListener = mainPresenter.doneListener
        mainPresenter.donePresenter = this.presenter
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            IDX_TODO_TAB -> todoTab
            IDX_DOING_TAB -> doingTab
            IDX_DONE_TAB -> doneTab
            else -> Fragment()
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getCount() = fragmentCount
}