package com.jjjoonngg.coroutinetoyproject.util.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.jjjoonngg.coroutinetoyproject.R
import kotlinx.android.synthetic.main.view_common_tab.view.*

class CustomTabLayout(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_common_tab, this, true)
    }

    private var tabLayoutClickListener: TabLayoutClickListener? = null

    fun addTabWithViewPager(menus: Array<String>, viewPager: ViewPager) {
        with(commonTabLayout) {
            commonTabLayout.setupWithViewPager(viewPager)
            for (i in menus.indices) {
                getTabAt(i)?.text = menus[i]
            }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

            })
        }
    }

    fun addTabWithoutViewPager(
        menus: Array<String>,
        tabLayoutClickListener: TabLayoutClickListener
    ) {
        this.tabLayoutClickListener = tabLayoutClickListener
        with(commonTabLayout) {
            for (i in menus.indices) {
                addTab(newTab().setText(menus[i]))
            }
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab) {
                    tabLayoutClickListener.clicked(tab.position)
                }

            })

        }
    }

    fun changeTabName(position: Int, newTabName: String) {
        commonTabLayout.getTabAt(position)?.text = newTabName
    }


    interface TabLayoutClickListener {
        fun clicked(position: Int)
    }
}