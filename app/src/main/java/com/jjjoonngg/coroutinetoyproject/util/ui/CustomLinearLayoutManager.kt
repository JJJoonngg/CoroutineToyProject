package com.jjjoonngg.coroutinetoyproject.util.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLinearLayoutManager :LinearLayoutManager{
    constructor(context: Context) : super(context)
    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}