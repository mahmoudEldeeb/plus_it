package com.fudex.plusit.models.repositrys

import com.fudex.plusit.models.PlusitModel
import com.fudex.plusit.models.daos.PlusitDao
import javax.inject.Inject

class PlursitRepositry
@Inject constructor(private val plusitDao: PlusitDao)
{
    suspend fun insertPlusit(title:String,catId:Int){
        var plusitModel=PlusitModel(catId = catId,title = title,total =0.0)
        plusitDao.insert(plusitModel)
    }
    suspend fun deleteplust(plusitModel: PlusitModel){
        plusitDao.delete(plusitModel)

    }
    suspend fun updatePlusit(plusitModel: PlusitModel){
        plusitDao.update( plusitModel)
    }
    suspend fun getPlusit(id:Int)=plusitDao.getAllPlusitFromCategory(id)
}