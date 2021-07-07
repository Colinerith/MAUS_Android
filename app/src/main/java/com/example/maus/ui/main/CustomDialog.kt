package com.example.maus.ui.main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TimePicker
import com.example.maus.R

class CustomDialog(context: Context) {
    private val dialog = Dialog(context)

    fun create(){
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.custom_dialog)

        //크기
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setTitle("Timer Setting")


        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        val timePicker = dialog.findViewById<TimePicker>(R.id.time)


        //확인버튼 누르면 timer페이지 갱신해야됨

        dialog.show()
    }
}