package com.fudex.plusit.views.adapters

import androidx.recyclerview.widget.RecyclerView




interface StartDragListener {

    fun requestDrag(viewHolder: RecyclerView.ViewHolder?)
}