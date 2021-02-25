package com.fudex.plusit.util
import android.app.Activity
import android.app.Dialog
import android.widget.Button
import android.widget.TextView
import com.fudex.plusit.R


lateinit var confirmDialog: Dialog
lateinit var message:TextView
lateinit var ok:Button

lateinit var cancel:Button

fun confirm(activity: Activity, mesage:String,confirmListener: ConfirmListener){
    confirmDialog = Dialog(activity)
    confirmDialog.setContentView(R.layout.confirm)
    message = confirmDialog.findViewById(R.id.message) as TextView
    ok = confirmDialog.findViewById(R.id.ok) as Button
    cancel = confirmDialog.findViewById(R.id.cancel) as Button
    message.text = mesage

    confirmDialog.show()
    ok.setOnClickListener {
        confirmDialog.dismiss()
        confirmListener.confirm()
    }

    cancel.setOnClickListener {
        confirmDialog.dismiss()
    }
}
