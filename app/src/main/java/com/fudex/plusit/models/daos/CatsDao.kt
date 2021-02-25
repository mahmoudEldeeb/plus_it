package com.fudex.plusit.models.daos

import androidx.room.*
import com.fudex.plusit.models.CategoryModel

@Dao
interface CatsDao {
    @Insert
    suspend fun insert( categoryModel: CategoryModel)

    @Update
   suspend fun update( categoryModel: CategoryModel)

    @Delete
   suspend fun delete( categoryModel: CategoryModel)

    @Query ("Delete from plusit where catId=:id ")
    suspend fun deletePlusitOfCategory(id:Int)

    @Query("Select * From categories")
    suspend fun getAllCategories(): List<CategoryModel>

}