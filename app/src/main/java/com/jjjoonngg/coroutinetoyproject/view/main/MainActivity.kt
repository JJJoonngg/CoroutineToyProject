package com.jjjoonngg.coroutinetoyproject.view.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.jjjoonngg.coroutinetoyproject.R
import com.jjjoonngg.coroutinetoyproject.util.REQUEST_CODE_OK
import com.jjjoonngg.coroutinetoyproject.view.add.AddActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_floating_action_button.view.*

class MainActivity : AppCompatActivity(R.layout.activity_main), MainContract.View,
    View.OnClickListener {


    private val presenter: MainPresenter by lazy {
        MainPresenter().apply {
            view = this@MainActivity
        }
    }

    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(presenter, supportFragmentManager)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fabButton.menuButton.setOnClickListener(this)
        fabButton.addButton.setOnClickListener(this)
        fabButton.refreshButton.setOnClickListener(this)
        fabButton.sortButton.setOnClickListener(this)
        initTab()
    }

    private fun initTab() {
        viewPager.adapter = mainAdapter
        val tabs = arrayOf(
            getString(R.string.todo),
            getString(R.string.doing),
            getString(R.string.done)
        )
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) = Unit

            override fun onPageSelected(position: Int) {
                presenter.getCurrPageIdx(position)
                if (fabButton.openFlag) fabButton.handlingButtons()
            }
        })
        bottomTab.addTabWithViewPager(tabs, viewPager)
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.addButton -> {
                val intent = Intent(this, AddActivity::class.java)
                this.startActivityForResult(intent, REQUEST_CODE_OK)
            }
        }
        fabButton.handlingButtons()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            presenter.clickAddButton()
        }
    }
}