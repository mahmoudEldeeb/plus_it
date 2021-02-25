package com.fudex.plusit.views

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fudex.plusit.databinding.ActivityPlusitBinding
import com.fudex.plusit.models.PlusitModel
import com.fudex.plusit.util.*
import com.fudex.plusit.views.adapters.PlusitAdapter
import com.fudex.plusit.views.adapters.StartDragListener
import com.fudex.plusit.views.adapters.swipe_drag_helper.ItemMoveCallback
import com.fudex.plusit.views.view_models.PlusitViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PlusitActivity : AppCompatActivity() ,StartDragListener{
lateinit var  touchHelper:ItemTouchHelper
    lateinit var  binding: ActivityPlusitBinding
    private val plusitViewModel by viewModels<PlusitViewModel>()
    lateinit var context: Activity
    private var plusitAdapter=PlusitAdapter()
    @Inject lateinit var sessionManger: SessionManger
     var id:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlusitBinding.inflate(layoutInflater)
            id=intent.getIntExtra("id", -1)
        var title=intent.getStringExtra("title" )
        var titleAfter=title
        if(title.length>15){
            titleAfter=title.substring(0,15)+"..."
        }
        binding.textView.text=titleAfter
        sessionManger.saveLastsCategory(id,title)
        context=this
        var layoutManager=LinearLayoutManager(this)
        binding.plusitRes.layoutManager = layoutManager

        val callback: ItemTouchHelper.Callback = ItemMoveCallback(plusitAdapter)
        touchHelper= ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.plusitRes)
        plusitAdapter.setStartDragListener(this)
        binding.plusitRes.adapter=plusitAdapter


        plusitViewModel.getplusit(id)

        plusitViewModel.plusits.observe(this, Observer {
            plusitAdapter.addValues(it)
        })

        binding.add.setOnClickListener {
            confirmAddCategory(this, "", object : EnterTitleListener {
                override fun title(title: String) {
                    plusitViewModel.insertNewPlusit(title, id)
                }
            })
        }

        plusitAdapter.setItemClick(object : PlusitAdapter.ItemClickListener {
            override fun onClick(plusitModel: PlusitModel) {
                TODO("Not yet implemented")
            }

            override fun delete(plusitModel: PlusitModel) {
                confirm(context, "are you sure you want delete this ", object :
                    ConfirmListener {
                    override fun confirm() {
                        plusitViewModel.deletePlusit(plusitModel)
                      //  plusitAdapter.notifyDataSetChanged()
                    }
                })

            }

            override fun update(plusitModel: PlusitModel) {
                confirmAddCategory(context, plusitModel.title, object : EnterTitleListener {
                    override fun title(title: String) {
                        plusitModel.title = title
                        plusitViewModel.updatePlusit(plusitModel)
                    }
                })
            }

            override fun updateForSwap(plusitModel: PlusitModel) {
                plusitViewModel.updatePlusit(plusitModel)
            }

            override fun changeAmount(plusitModel: PlusitModel) {
                plusitViewModel.updatePlusit(plusitModel)
            }

        })

        setContentView(binding.root)

    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let { touchHelper.startDrag(it) }
    }

    override fun onBackPressed() {
        sessionManger.saveLastsCategory(-1,"")
        super.onBackPressed()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

}