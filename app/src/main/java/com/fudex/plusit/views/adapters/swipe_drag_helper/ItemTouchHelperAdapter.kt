package com.fudex.plusit.views.adapters.swipe_drag_helper

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemSwiped(position: Int)
}