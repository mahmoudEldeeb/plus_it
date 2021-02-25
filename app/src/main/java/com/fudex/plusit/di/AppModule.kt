package com.fudex.plusit.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.fudex.plusit.models.DatabaseModule
import com.fudex.plusit.models.daos.CatsDao
import com.fudex.plusit.models.daos.PlusitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application):Context
    {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideDatabase(application: Application):DatabaseModule
    {
        return  Room.databaseBuilder(
            application.applicationContext,
            DatabaseModule::class.java,
            "plursite"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(databaseModule: DatabaseModule): CatsDao
    {
        return databaseModule.categoryDao()
    }

    @Provides
    @Singleton
    fun providePlusitDao(databaseModule: DatabaseModule): PlusitDao
    {
        return databaseModule.plusitDao()
    }
    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "plusit",
            Context.MODE_PRIVATE
        )
    }

}