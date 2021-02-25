package com.fudex.plusit.views.adapters.swipe_drag_helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.fudex.plusit.views.adapters.PlusitAdapter


class ItemMoveCallback constructor(private var mAdapter: ItemTouchHelperContract) : ItemTouchHelper.Callback() {


    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        mAdapter?.onRowMoved(viewHolder.adapterPosition, target.adapterPosition);
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }


    override fun onSelectedChanged(
        viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is PlusitAdapter.ViewHolder) {
                val myViewHolder: PlusitAdapter.ViewHolder? =
                    viewHolder as PlusitAdapter.ViewHolder?
                mAdapter!!.onRowSelected(myViewHolder)
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

   override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView!!, viewHolder!!)
        if (viewHolder is PlusitAdapter.ViewHolder) {
            val myViewHolder: PlusitAdapter.ViewHolder? =
                viewHolder as PlusitAdapter.ViewHolder?
            mAdapter!!.onRowClear(myViewHolder)
        }
    }


    interface ItemTouchHelperContract {
        fun onRowMoved(fromPosition: Int, toPosition: Int)
        fun onRowSelected(myViewHolder: PlusitAdapter.ViewHolder?)
        fun onRowClear(myViewHolder: PlusitAdapter.ViewHolder?)
    }

}
