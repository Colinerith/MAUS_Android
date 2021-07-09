package com.example.maus.ui.main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.example.maus.R

class TimerSettingDialog(context: Context) {
    private val dialog = Dialog(context)
    val context = context

    fun create(){
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_timer_setting)

        //크기
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        val timePicker = dialog.findViewById<TimePicker>(R.id.time)

        val saveBtn = dialog.findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{
            Toast.makeText(context,"${timePicker.hour} : ${timePicker.minute}", Toast.LENGTH_SHORT).show()
        }

        val dateBtn = dialog.findViewById<Button>(R.id.dateBtn)
        dateBtn.setOnClickListener{
            val dialog2 = TimerSettingDateDialog(context)
            dialog2.create()
        }

        //확인버튼 누르면 timer페이지 갱신해야됨

        dialog.show()
    }
}