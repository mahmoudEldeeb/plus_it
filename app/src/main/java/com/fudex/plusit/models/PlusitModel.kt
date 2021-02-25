package com.fudex.plusit.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "plusit")
data class PlusitModel(
    @PrimaryKey (autoGenerate = true)
     val id : Int?=null,
    val catId:Int,
    var title : String,
    var total: Double
)
