package com.jjjoonngg.coroutinetoyproject.view.done

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.util.ui.CustomLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_done.*

class DoneFragment : Fragment(R.layout.fragment_done), DoneContract.View {
    val presenter: DonePresenter by lazy {
        DonePresenter().apply {
            view = this@DoneFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize()
    }

    override fun setAdapter(adapter: DoneRecyclerViewAdapter) {
        with(doneRecyclerView) {
            val customLinearLayoutManager = CustomLinearLayoutManager(context!!)
            layoutManager = customLinearLayoutManager
            this.adapter = adapter
        }
    }

}