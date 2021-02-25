package com.fudex.plusit.views.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fudex.plusit.R
import com.fudex.plusit.models.CategoryModel
import com.fudex.plusit.views.adapters.swipe_drag_helper.ItemMoveCallback
import kotlinx.android.synthetic.main.item_category.view.*
import java.util.*


class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() , ItemMoveCallback.ItemTouchHelperContract{
    interface ItemClickListener{
        fun onClick(categoryModel: CategoryModel)

        fun delete(categoryModel: CategoryModel)

        fun update(categoryModel: CategoryModel)

        fun updateForSwap(categoryModel: CategoryModel)

    }

    lateinit var mStartDragListener: StartDragListener

    lateinit var context: Context
    lateinit var itemClickListener: ItemClickListener
    private var itemsList:MutableList<CategoryModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        context=parent.context

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=itemsList[position]
        holder.itemView.titleCat.text=item.title

        holder.itemView.delete.setOnClickListener {
            itemClickListener.delete(item)
        }
        holder.itemView.edite.setOnClickListener {
            itemClickListener.update(item)
        }
        holder.itemView.card.setOnClickListener {
            Log.v("ddddd","clock")
            itemClickListener.onClick(item)
        }
        holder.itemView.setOnTouchListener { v, event ->
            Log.v("ddddd","touch")
            if (event?.action ==
                MotionEvent.ACTION_DOWN
            ) {
                mStartDragListener.requestDrag(holder)
            }
            else{
            }

            false
        }

    }

    fun addValues(list: List<CategoryModel>){
        if(itemsList.isNotEmpty())
            itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
    fun setItemClick(itemClockListener: ItemClickListener){
        this.itemClickListener=itemClockListener
    }
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }


    fun setStartDragListener( lisiner: StartDragListener){
        mStartDragListener=lisiner
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {


        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {

                Collections.swap(itemsList, i, i + 1)
                var from=itemsList[i]
                var to=itemsList[i+1]

                var temp= CategoryModel(from.id,to.title)
                itemClickListener.updateForSwap(temp)
                temp= CategoryModel(to.id,from.title)
                itemClickListener.updateForSwap(temp)

            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemsList, i, i - 1)
                var from=itemsList[i]
                var to=itemsList[i-1]
                var temp= CategoryModel(from.id,to.title)
                itemClickListener.updateForSwap(temp)
                temp= CategoryModel(to.id,from.title)
                itemClickListener.updateForSwap(temp)

            }
        }
        notifyItemMoved(fromPosition, toPosition)


    }

    override fun onRowSelected(myViewHolder: PlusitAdapter.ViewHolder?) {
        myViewHolder?.itemView?.card?.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(myViewHolder: PlusitAdapter.ViewHolder?) {

        myViewHolder?.itemView?.card?.setBackgroundColor(Color.WHITE);
    }
}