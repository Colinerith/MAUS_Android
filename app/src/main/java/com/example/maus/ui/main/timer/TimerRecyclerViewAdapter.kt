package com.example.maus.ui.main.timer

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.maus.R
import com.example.maus.data.TimerItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimerRecyclerViewAdapter(private var timerList: ArrayList<TimerItem>) :
    RecyclerView.Adapter<TimerRecyclerViewAdapter.ViewHolder>() {

    // ViewHolder단위 객체로 View의 데이터 설정
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var timeTextView: TextView = view.findViewById(R.id.timeTextView)
        var dateDayTextView: TextView = view.findViewById(R.id.dateDayTextView)
        var stateSwitch: Switch = view.findViewById(R.id.onoffSwitch)
        var actionTextView: TextView = view.findViewById(R.id.actionTextView)
        var context: Context = view.context
        var days = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        var path = "Timer/a"
    }

    // 보여줄 아이템 개수만큼 View 생성
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_timer, viewGroup, false)

        return ViewHolder(view)
    }

    // 생성된 View에 보여줄 데이터 결정
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val hour = timerList[position].hour
        val minute = timerList[position].minute
        //var temp = LocalDateTime.of(0, 0, 0, hour.toInt(), minute.toInt())
        //var time = temp.format(DateTimeFormatter.ofPattern("HH:mm"))

        var time = ""
        Log.d("viewholder: ", "$hour:${minute.toInt()}")
        if(hour.toInt() > 12)
            time = "${LocalDateTime.of(1, 1, 1, hour.toInt()-12, minute.toInt()).format(DateTimeFormatter.ofPattern("HH:mm"))} PM"
        else if (hour.toInt() == 12)
            time = "${LocalDateTime.of(1, 1, 1, hour.toInt(), minute.toInt()).format(DateTimeFormatter.ofPattern("HH:mm"))} PM"
        else
            time = "${LocalDateTime.of(1, 1, 1, hour.toInt(), minute.toInt()).format(DateTimeFormatter.ofPattern("HH:mm"))} AM"


//        if(hour.toInt() >= 22){ // pm 10 ~ 12
//            if(minute.toInt() < 10)
//                time = "${hour.toInt() - 12}:0$minute PM"
//            else
//                time = "${hour.toInt() - 12}:$minute PM"
//        }
//        else if(hour.toInt() > 12){ // pm 1~9
//
//        }
//        else if(hour.toInt() == 12){
//
//        }
//        else if(hour.toInt()<10){
//
//        }
//        if(hour.toInt() < 10){
//            if(minute.toInt()<10)
//                time = "0$hour:0$minute AM"
//        }
        val day = timerList[position].day
        val date = timerList[position].date
        var dateDay = ""
        if (date != "null") dateDay = date
        else {
            if(day == "1111111") dateDay = "Everyday"
            else {
                var dayStr = ""
                for (i in 0..6)
                    if (day[i] == '1') dayStr += viewHolder.days[i] + ", "
                dateDay = dayStr.substringBeforeLast(",")
            }
        }
        var state = timerList[position].state == "1"
        val turningOn = timerList[position].turningOn
        val actionText =
            if (turningOn) viewHolder.context.getString(R.string.turningOn)
            else viewHolder.context.getString(R.string.turningOff)

        // on/off switch 클릭
        viewHolder.stateSwitch.setOnClickListener {
            //viewHolder.stateSwitch.isChecked = !viewHolder.stateSwitch.isChecked
            val ref : DatabaseReference = FirebaseDatabase.getInstance().getReference(viewHolder.path + "/" + timerList[position].key)
            if(state) {
                ref.child("state").setValue("0")
                viewHolder.stateSwitch.isChecked = false
            }
            else {
                ref.child("state").setValue("1")
                viewHolder.stateSwitch.isChecked = true
            }
            state = !state
            //ref.child("state").setValue((!state).toString()) //]
            Log.d("switch: ", state.toString())
        }

        // 아이템 클릭
        viewHolder.itemView.setOnClickListener {
            val dialog = TimerSettingDialog(viewHolder.context)
            dialog.setting(hour.toInt(), minute.toInt(), date, day, turningOn, timerList[position].key)
        }
    
        // 타이머 시간
        viewHolder.timeTextView.text = time
        // 타이머 날짜 또는 요일
        viewHolder.dateDayTextView.text = dateDay
        //타이머 on/off
        viewHolder.stateSwitch.isChecked = state
        // 불을 끄는/ 켜는 동작
        viewHolder.actionTextView.text = actionText
    }

    // 보여줄 아이템이 몇 개인지
    override fun getItemCount() = timerList.size

    fun submitList(timerList: ArrayList<TimerItem>){
        this.timerList = timerList
    }
}