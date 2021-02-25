package com.fudex.plusit.views.view_models

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fudex.plusit.models.CategoryModel
import com.fudex.plusit.models.repositrys.CategoryRepositry
import kotlinx.coroutines.launch

class CategoryViewModel @ViewModelInject constructor(private val categoryRepositry: CategoryRepositry,
                                                     @Assisted private val savedStateHandle: SavedStateHandle) : ViewModel()
{


    val categories: MutableLiveData<List<CategoryModel>> = MutableLiveData<List<CategoryModel>>()
fun getAllCategories(){
    viewModelScope.launch {
        categories.value=categoryRepositry.getAllCategories()
    }
}
fun insertNewCategroy(title:String) {
    viewModelScope.launch {
        categoryRepositry.insertNewCategory(title)
        getAllCategories()
    }
}

    fun updateCategory(categoryModel: CategoryModel) {
        viewModelScope.launch {
            categoryRepositry.updateCategory(categoryModel)
            getAllCategories()
        }
    }
        fun deleteCategory(categoryModel: CategoryModel){
            viewModelScope.launch {
                categoryRepositry.deleteNewCategory(categoryModel)
                getAllCategories()
            }
}
}