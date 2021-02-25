package com.fudex.plusit.util

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManger @Inject constructor(private val preferences: SharedPreferences) {
fun saveLastsCategory(id:Int,title:String){
    val editor: SharedPreferences.Editor = preferences.edit()
    editor.putInt("id",id)
    editor.putString("lastCategoryTitle",title)
    editor.apply()
}


    fun lastCategoryId()=preferences.getInt("id",-1)
    fun lastCategoryTitle()=preferences.getString("lastCategoryTitle","")

    fun isFirstTime()=preferences.getBoolean("firstTime",true)
    fun setFirstTime(){
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putBoolean("firstTime",false)
        editor.apply()
    }

}