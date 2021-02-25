package com.fudex.plusit.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "categories")
data class CategoryModel(
    @PrimaryKey (autoGenerate = true)
     var id : Int?=null,
    var title : String
)
