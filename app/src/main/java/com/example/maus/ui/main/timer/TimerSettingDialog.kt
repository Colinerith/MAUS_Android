package com.example.maus.ui.main.timer

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.maus.R

class TimerSettingDialog(context: Context) {
    private val dialog = Dialog(context)
    val context = context
    lateinit var timePicker: TimePicker
    lateinit var datedayTextView: TextView
    lateinit var radioButtonOn: RadioButton
    lateinit var radioButtonOff: RadioButton
//    lateinit var dateDayTextView: TextView
//    lateinit var actionTextView: TextView
    lateinit var days: List<String>

    fun create(){
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_timer_setting)

        //크기
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        timePicker = dialog.findViewById(R.id.time)
        datedayTextView = dialog.findViewById(R.id.settingDateDayTextView)
        radioButtonOn = dialog.findViewById(R.id.radioButtonOn)
        radioButtonOff = dialog.findViewById(R.id.radioButtonOff)
//        radioButtonOn.setBackgroundColor(Color.rgb(0,0,0))
//        radioButtonOff.setBackgroundColor(Color.rgb(0,0,0))
        radioButtonOn.isChecked = true
//        radioButtonOff.isChecked = false
        days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

        // 취소 버튼
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

//        timeTextView = dialog.findViewById(R.id.timeTextView)
//        dateDayTextView = dialog.findViewById(R.id.dateDayTextView)
//        actionTextView = dialog.findViewById(R.id.actionTextView)

        // 저장 버튼
        val saveBtn = dialog.findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{
            Toast.makeText(context,"${timePicker.hour} : ${timePicker.minute}", Toast.LENGTH_SHORT).show()
        }

        // 날짜를 지정할 수 있는 달력 버튼
        val dateBtn = dialog.findViewById<ImageButton>(R.id.dateBtn)
        dateBtn.setOnClickListener{
            val dialog2 = TimerSettingDateDialog(context)
            dialog2.create()
        }

        //확인 버튼 누르면 timer페이지 갱신해야됨 -> 이벤트 리스너 달아놓으면 안 해도 될듯?

        //dialog.show()
    }

    // 리스트에서 아이템을 클릭해 다이얼로그 띄웠을 경우 값 세팅
    fun setting(hour:Int, minute:Int, date:String, day:String, turningOn:Boolean){
        create()

        timePicker.hour = hour
        timePicker.minute = minute
        if(date != "null")
            datedayTextView.text = date
        else {
            // date는 달력에서 받아옴. 기본값은 '오늘'
            // 칩 day값에 따라 세팅
            var dayStr = ""
            if(day == "1111111") dayStr = "Everyday"
            else{
                for (i in 0..6)
                    if (day[i] == '1') dayStr += days[i] + ", "
            }
            datedayTextView.text = dayStr.substringBeforeLast(',')
        }
        if(turningOn){
            radioButtonOn.isChecked = true
        }
        else{
            radioButtonOff.isChecked = true
        }
        showDialog()
    }

    fun showDialog(){
        dialog.show()
    }
}