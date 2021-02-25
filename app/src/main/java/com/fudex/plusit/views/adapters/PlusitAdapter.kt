package com.fudex.plusit.views.adapters

import android.content.Context
import android.graphics.Color
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.fudex.plusit.R
import com.fudex.plusit.models.PlusitModel
import com.fudex.plusit.views.adapters.swipe_drag_helper.ItemMoveCallback
import kotlinx.android.synthetic.main.item_plursit.view.*
import java.util.*


class PlusitAdapter : RecyclerSwipeAdapter<PlusitAdapter.ViewHolder>() , ItemMoveCallback.ItemTouchHelperContract{
     interface ItemClickListener{
        fun onClick(plusitModel: PlusitModel)
         fun delete(plusitModel: PlusitModel)
         fun update(plusitModel: PlusitModel)

         fun updateForSwap(plusitModel: PlusitModel)
         fun changeAmount(plusitModel: PlusitModel)
    }

    lateinit var mStartDragListener: StartDragListener
    lateinit var context: Context
    lateinit var itemClickListener: ItemClickListener
    private var itemsList:MutableList<PlusitModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plursit, parent, false)
        context=parent.context

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = itemsList[position]
        holder.itemView.titleOfPlusit.text=item.title
        holder.itemView.total.text=item.total.toString()

        holder.itemView.editeTite.setOnClickListener {
            itemClickListener.update(item)
        }
        holder.itemView.addAmount.setOnClickListener {
            if(holder.itemView.amount.text.isNotEmpty()) {
                var amount: Double = holder.itemView.amount.text.toString().toDouble()
                item.total = item.total + amount
                holder.itemView.amount.text.clear()
                notifyItemChanged(position)
                itemClickListener.changeAmount(item)
            }
        }
        holder.itemView.sub.setOnClickListener {
            if(holder.itemView.amount.text.isNotEmpty())
            {
            var amount:Double=holder.itemView.amount.text.toString().toDouble()
            item.total=item.total-amount
                holder.itemView.amount.text.clear()
            notifyItemChanged(position)
            itemClickListener.changeAmount(item)
            }
        }
        holder.itemView.deletePlursit.setOnClickListener {
            itemClickListener.delete(item)
            holder.itemView.swipe.close()
        }

        holder.itemView.setOnTouchListener { v, event ->
            if (event?.action ==
                MotionEvent.ACTION_DOWN
            ) {
                mStartDragListener.requestDrag(holder)
            }
            false
        }
    }
    fun addValues(list: List<PlusitModel>){
        if(itemsList.isNotEmpty())
            itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }
    fun setItemClick(itemClockListener: ItemClickListener){
        this.itemClickListener=itemClockListener
    }
  class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

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

                var temp=PlusitModel(from.id,to.catId,to.title,to.total)
                itemClickListener.updateForSwap(temp)
                temp= PlusitModel(to.id,from.catId,from.title,from.total)
                itemClickListener.updateForSwap(temp)

            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemsList, i, i - 1)
                var from=itemsList[i]
                var to=itemsList[i-1]
                var temp=PlusitModel(from.id,to.catId,to.title,to.total)
                itemClickListener.updateForSwap(temp)
                temp= PlusitModel(to.id,from.catId,from.title,from.total)
                itemClickListener.updateForSwap(temp)

            }
        }
        notifyItemMoved(fromPosition, toPosition)


    }

    override fun onRowSelected(myViewHolder: ViewHolder?) {
        myViewHolder?.itemView?.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(myViewHolder: ViewHolder?) {

        myViewHolder?.itemView?.setBackgroundColor(Color.WHITE);
    }
}