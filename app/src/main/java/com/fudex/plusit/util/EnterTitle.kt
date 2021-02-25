package com.fudex.plusit.util
import android.app.Activity
import android.app.Dialog
import android.widget.Button
import android.widget.TextView
import com.fudex.plusit.R

lateinit var titleDialog: Dialog
lateinit var title:TextView
lateinit var confirm:Button

lateinit var cance:Button

fun confirmAddCategory(activity: Activity, titl:String,confirmListener: EnterTitleListener){
    titleDialog = Dialog(activity)
    titleDialog.setContentView(R.layout.add_title)
    title = titleDialog.findViewById(R.id.title) as TextView
     confirm = titleDialog.findViewById(R.id.ok) as Button
    cance = titleDialog.findViewById(R.id.cancel) as Button
    title.text = titl

    titleDialog.show()
    confirm.setOnClickListener {
        titleDialog.dismiss()
        confirmListener.title(title.text.toString())
    }

    cance.setOnClickListener {
        titleDialog.dismiss()
    }
}
