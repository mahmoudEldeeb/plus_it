package com.fudex.plusit.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fudex.plusit.databinding.ActivityCategoryBinding
import com.fudex.plusit.R
import com.fudex.plusit.models.CategoryModel
import com.fudex.plusit.util.*
import com.fudex.plusit.views.adapters.CategoriesAdapter
import com.fudex.plusit.views.adapters.StartDragListener
import com.fudex.plusit.views.adapters.swipe_drag_helper.ItemMoveCallback
import com.fudex.plusit.views.view_models.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CategoryActivity : AppCompatActivity() ,StartDragListener{
    lateinit var  touchHelper: ItemTouchHelper
    @Inject
    lateinit var sessionManger: SessionManger
    lateinit var  binding: ActivityCategoryBinding
    private val categoryViewModel by viewModels<CategoryViewModel>()
        private val categoriesAdapter=CategoriesAdapter()
    lateinit var context:Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        binding= ActivityCategoryBinding.inflate(layoutInflater)

        context=this
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if(sessionManger.isFirstTime())
        {
            welcome(this)
            sessionManger.setFirstTime()
        }

        if(sessionManger.lastCategoryId()!=-1){
            var intent= Intent(context,PlusitActivity::class.java)
            intent.putExtra("id",sessionManger.lastCategoryId())
            intent.putExtra("title",sessionManger.lastCategoryTitle())
            startActivity(intent)
        }

        binding.catsRes.layoutManager = LinearLayoutManager(this)

        val callback: ItemTouchHelper.Callback = ItemMoveCallback(categoriesAdapter)
        touchHelper= ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.catsRes)
        categoriesAdapter.setStartDragListener(this)
        binding.catsRes.adapter=categoriesAdapter


        categoryViewModel.getAllCategories()
        categoryViewModel.categories.observe(this, Observer {
                categoriesAdapter.addValues(it)
        })

        binding.add.setOnClickListener {
            confirmAddCategory(this,"",object :EnterTitleListener{
                override fun title(title: String) {
                    categoryViewModel.insertNewCategroy(title)
                }
            })
        }


        categoriesAdapter.setItemClick(object : CategoriesAdapter.ItemClickListener{
            override fun onClick(categoryModel: CategoryModel) {

                var intent= Intent(context,PlusitActivity::class.java)
                intent.putExtra("id",categoryModel.id)
                intent.putExtra("title",categoryModel.title)
                startActivity(intent)
            }
            override fun delete(categoryModel: CategoryModel) {
                confirm(context,"are you sure you want delete this category",object :ConfirmListener{
                    override fun confirm() {
                        categoryViewModel.deleteCategory(categoryModel)
                    }
                })
            }

            override fun update(categoryModel: CategoryModel) {
                confirmAddCategory(context,categoryModel.title,object :EnterTitleListener{
                    override fun title(title: String) {
                        var model=CategoryModel(title = title)
                        model.id=categoryModel.id
                        categoryViewModel.updateCategory(model)
                    }
                })
            }

            override fun updateForSwap(categoryModel: CategoryModel) {

                categoryViewModel.updateCategory(categoryModel)
            }


        })
    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let { touchHelper.startDrag(it) }
    }
}