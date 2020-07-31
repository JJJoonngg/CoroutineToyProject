package com.jjjoonngg.coroutinetoyproject.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import com.jjjoonngg.coroutinetoyproject.R
import kotlinx.android.synthetic.main.view_floating_action_button.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("ViewConstructor")
class CustomFloatingActionButtonUI(
    context: Context,
    attributeSet: AttributeSet? = null
) :
    ConstraintLayout(context, attributeSet){

    private var fabOpen: Animation
    private var fabClose: Animation
    var openFlag = true


    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_floating_action_button, this, true)

        fabOpen = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(context, R.anim.fab_close)

        initButtons()
    }

    private fun initButtons() {
        anim()
    }

    fun handlingButtons() {
        GlobalScope.launch(Dispatchers.Main) {
            with(menuButton) {
                anim()
            }
        }
    }

    private fun anim() {
        if (openFlag) {
            addButton.startAnimation(fabClose)
            refreshButton.startAnimation(fabClose)
            sortButton.startAnimation(fabClose)

            addButton.isClickable = false
            refreshButton.isClickable = false
            sortButton.isClickable = false

            menuButton.setImageResource(R.drawable.ic_baseline_menu_24)
        } else {
            addButton.startAnimation(fabOpen)
            refreshButton.startAnimation(fabOpen)
            sortButton.startAnimation(fabOpen)

            addButton.isClickable = true
            refreshButton.isClickable = true
            sortButton.isClickable = true

            menuButton.setImageResource(R.drawable.ic_baseline_menu_open_24)
        }
        openFlag = !openFlag

    }
}