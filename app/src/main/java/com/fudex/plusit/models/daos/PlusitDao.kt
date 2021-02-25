package com.fudex.plusit.models.daos

import androidx.room.*
import com.fudex.plusit.models.PlusitModel

@Dao
interface PlusitDao {
    @Insert
    suspend fun insert( plusitModel: PlusitModel)

    @Update
   suspend fun update( plusitModel: PlusitModel)

    @Delete
   suspend fun delete( plusitModel: PlusitModel)

    @Query("Select * From plusit where catId=:id Order by id desc")
    suspend fun getAllPlusitFromCategory(id:Int): List<PlusitModel>

}