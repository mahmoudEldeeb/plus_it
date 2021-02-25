package com.fudex.plusit.util
import android.app.Activity
import android.app.Dialog
import android.widget.Button
import com.fudex.plusit.R



lateinit var closeWelcome:Button

fun welcome(activity: Activity){

     var welcomeDialog= Dialog(activity)
    welcomeDialog.setContentView(R.layout.welcom_screen)
    closeWelcome=welcomeDialog.findViewById(R.id.closeButton)
    welcomeDialog.show()
    closeWelcome.setOnClickListener {
        welcomeDialog
            .dismiss()

    }

}
