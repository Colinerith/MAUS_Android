package com.example.maus.ui.main.timer

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.example.maus.R
import com.example.maus.data.TimerItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TimerSettingDialog(context: Context) {
    private val dialog = Dialog(context)
    val context = context
    lateinit var timePicker: TimePicker
    lateinit var datedayTextView: TextView
    lateinit var radioButtonOn: RadioButton
    lateinit var radioButtonOff: RadioButton
    lateinit var chipGroup: ChipGroup
    lateinit var chips: List<Chip>
    var daysText = ""
    lateinit var days: List<String>
    var saveKey = ""
    var mode = "create"
    var path = "Timer/a"

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
        radioButtonOn.isChecked = true
        days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        chips = listOf(dialog.findViewById<Chip>(R.id.sunChip),
                dialog.findViewById(R.id.monChip), dialog.findViewById(R.id.tueChip),
                dialog.findViewById(R.id.wedChip), dialog.findViewById(R.id.thuChip),
                dialog.findViewById(R.id.friChip), dialog.findViewById(R.id.satChip))

        chipGroup = dialog.findViewById(R.id.chipGroup)

        for(i in 0..6){
            chips[i].setOnCheckedChangeListener { _, _ ->
                var dayStr = ""
                for(j in 0..6){
                    if (chips[j].isChecked) dayStr += days[j] + " "
                }
                Log.d("setting",dayStr)
                if(dayStr == "Sun Mon Tue Wed Thu Fri Sat ")
                    datedayTextView.text = "Everyday"
                else if(dayStr != "")
                    datedayTextView.text = dayStr
                else
                    datedayTextView.text = "2020.1.1"
            }
        }


//        chipGroup.setOnClickListener{
//            Log.d("setting","listen")
//            var dayStr = ""
//            for(i in 0..6){
//                if (chips[i].isChecked) dayStr += days[i] + " "
//            }
//            if(dayStr != "")
//                datedayTextView.text = dayStr
//            else
//                datedayTextView.text = "2020.1.1"
//        }

        // 취소 버튼
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }

        // 저장 버튼 - 추가
        val saveBtn = dialog.findViewById<Button>(R.id.saveBtn)
        saveBtn.setOnClickListener{
            val data = getDialogData()
            val timerValues = data.toMap()
            val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference(path)

            if(mode == "create"){
                saveKey = ref.push().key.toString()
                val childUpdates = hashMapOf<String, Any>(
                        "/$saveKey" to timerValues
                )
                ref.updateChildren(childUpdates)
            }
            else if(mode == "modify"){
                val childUpdates = hashMapOf<String, Any>(
                        "/$saveKey" to timerValues
                )
                ref.updateChildren(childUpdates)
            }

            dialog.dismiss()
            Toast.makeText(dialog.context, "Saved.", Toast.LENGTH_SHORT).show()
        }

        // 날짜를 지정할 수 있는 달력 버튼
        val dateBtn = dialog.findViewById<ImageButton>(R.id.dateBtn)
        dateBtn.setOnClickListener{
            val dialog2 = TimerSettingDateDialog(context)
            dialog2.create()
        }
    }

    // 리스트에서 아이템을 클릭해 다이얼로그 띄웠을 경우 값 세팅
    fun setting(hour:Int, minute:Int, date:String, day:String, turningOn:Boolean, key:String){
        saveKey=key
        mode = "modify"
        create()

        timePicker.hour = hour
        timePicker.minute = minute
        if(date != "null")
            datedayTextView.text = date
        else {
            // date는 달력에서 받아옴. 기본값은 '오늘'. 칩 day 값에 따라 세팅
            var dayStr = ""
            if(day == "1111111") dayStr = "Everyday"
            else {
                for (i in 0..6)
                    if (day[i] == '1') dayStr += days[i] + ", "
            }
            datedayTextView.text = dayStr.substringBeforeLast(',')
        }
        if(turningOn) radioButtonOn.isChecked = true
        else radioButtonOff.isChecked = true

        showDialog()
    }

    private fun getDialogData(): TimerItem {
        val date = "2021.1.1" //
        var day = "" //
        val hour = timePicker.hour.toString()
        val minute = timePicker.minute.toString()
        val turningOn = radioButtonOn.isChecked

        for(i in 0..6)
            day += if(chips[i].isChecked) "1" else "0"

        return TimerItem("", date, day, hour, minute, "1", turningOn)
    }

    fun showDialog(){
        dialog.show()
    }
}