package com.fudex.plusit.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fudex.plusit.models.daos.CatsDao
import com.fudex.plusit.models.daos.PlusitDao

@Database(entities = [CategoryModel::class,PlusitModel::class], version = 1, exportSchema = false)
    abstract class DatabaseModule : RoomDatabase() {
        abstract fun categoryDao(): CatsDao
    abstract fun plusitDao(): PlusitDao
    }