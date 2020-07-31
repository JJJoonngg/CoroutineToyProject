package com.jjjoonngg.coroutinetoyproject.view.doing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.util.ui.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_doing.*


class DoingFragment : Fragment(R.layout.fragment_doing), DoingContract.View {
    val presenter: DoingPresenter by lazy {
        DoingPresenter().apply {
            view = this@DoingFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize()
    }

    override fun setAdapter(adapter: DoingRecyclerViewAdapter) {
        with(doingRecyclerView) {
            val customLinearLayoutManager = CustomLinearLayoutManager(context!!)
            layoutManager = customLinearLayoutManager
            this.adapter = adapter
        }
    }
}