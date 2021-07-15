package com.example.maus.ui.main.timer

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.maus.R
import java.time.LocalDate
import java.time.Year

class TimerSettingDateDialog(context: Context) {
    private val dialog = Dialog(context)
    val context = context
    lateinit var datePicker: DatePicker

    @RequiresApi(Build.VERSION_CODES.O)
    fun create(dateDayTextView: TextView){
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_date_setting)

        //크기
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val cancelBtn = dialog.findViewById<Button>(R.id.cancelDateBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        datePicker = dialog.findViewById<DatePicker>(R.id.datePicker)
        datePicker.init(LocalDate.now().year, LocalDate.now().monthValue - 1, LocalDate.now().dayOfMonth + 1, null)

        val saveDateBtn = dialog.findViewById<Button>(R.id.saveDateBtn)
        saveDateBtn.setOnClickListener{
            val y = datePicker.year
            val m = datePicker.month + 1
            val d = datePicker.dayOfMonth
            if(m< 10){
                if(d<10)
                    dateDayTextView.text = "$y-0$m-0$d"
                else
                    dateDayTextView.text = "$y-0$m-$d"
            }
            else{
                if(d<10)
                    dateDayTextView.text = "$y-$m-0$d"
                else
                    dateDayTextView.text = "$y-$m-$d"
            }
            dialog.dismiss()
        }

        dialog.show()
    }
}