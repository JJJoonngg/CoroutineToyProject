package com.jjjoonngg.coroutinetoyproject.view.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.util.ui.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_todo.*

class TodoFragment : Fragment(R.layout.fragment_todo), TodoContract.View {

    val presenter: TodoPresenter by lazy {
        TodoPresenter().apply {
            view = this@TodoFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize()
    }

    override fun setAdapter(adapter: TodoRecyclerViewAdapter) {
        with(todoRecyclerView) {
            val customLinearLayoutManager = CustomLinearLayoutManager(context!!)
            layoutManager = customLinearLayoutManager
            this.adapter = adapter
        }
    }
}