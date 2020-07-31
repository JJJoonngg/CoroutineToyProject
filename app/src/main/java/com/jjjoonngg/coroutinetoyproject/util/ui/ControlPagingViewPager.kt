package com.jjjoonngg.coroutinetoyproject.util.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.ViewPager

class ControlPagingViewPager(context: Context, attributeSet: AttributeSet? = null) :
    ViewPager(context, attributeSet) {

    private var enable = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (enable) {
            super.onInterceptTouchEvent(ev)
        } else {
            if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
            } else {
                if (super.onInterceptTouchEvent(ev)) {
                    super.onTouchEvent(ev)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (enable) {
            super.onTouchEvent(ev)
        } else {
            MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(
                ev
            )
        }
    }

    fun enablePaging(enable: Boolean) {
        this.enable = enable
    }
}