package com.example.maus.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.maus.R

class CustomDialog2(context: Context) {
    private val dialog = Dialog(context)
    val context = context
    //lateinit var navController: NavController

    fun create(){
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        dialog.setContentView(R.layout.dialog_date_setting)

        //크기
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        //dialog.setTitle("Date Setting")


        val cancelBtn = dialog.findViewById<Button>(R.id.cancelDateBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        val datePicker = dialog.findViewById<DatePicker>(R.id.datePicker)

        val saveDateBtn = dialog.findViewById<Button>(R.id.saveDateBtn)
        saveDateBtn.setOnClickListener{
            Toast.makeText(context,"${datePicker.month + 1} : ${datePicker.dayOfMonth}", Toast.LENGTH_SHORT).show()
        }

        //확인버튼 누르면 timer페이지 갱신해야됨

        dialog.show()
    }
}