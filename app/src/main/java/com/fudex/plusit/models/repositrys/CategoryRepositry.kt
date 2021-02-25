package com.fudex.plusit.models.repositrys

import com.fudex.plusit.models.CategoryModel
import com.fudex.plusit.models.daos.CatsDao
import javax.inject.Inject

class CategoryRepositry
@Inject constructor(private val catsDao: CatsDao)
{
    suspend fun insertNewCategory(title:String){
       var categoryModel= CategoryModel(title = title)
        catsDao.insert(categoryModel)
    }
    suspend fun deleteNewCategory(categoryModel: CategoryModel){
        catsDao.delete(categoryModel)
            catsDao.deletePlusitOfCategory(categoryModel?.id!!)
    }
    suspend fun updateCategory(categoryModel: CategoryModel){
        catsDao.update(categoryModel)
    }

    suspend fun getAllCategories()=catsDao.getAllCategories()
}