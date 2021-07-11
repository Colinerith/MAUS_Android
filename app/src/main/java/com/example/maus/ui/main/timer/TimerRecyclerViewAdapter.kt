package com.example.maus.ui.main.timer

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.maus.R
import com.example.maus.data.TimerItem

class TimerRecyclerViewAdapter(private var timerList: ArrayList<TimerItem>) :
    RecyclerView.Adapter<TimerRecyclerViewAdapter.ViewHolder>() {

    // ViewHolder단위 객체로 View의 데이터 설정
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var timeTextView: TextView = view.findViewById(R.id.timeTextView)
        var dateDayTextView: TextView = view.findViewById(R.id.dateDayTextView)
        var state: SwitchCompat = view.findViewById(R.id.onoffSwitch)
        var actionTextView: TextView = view.findViewById(R.id.actionTextView)
        var context: Context = view.context
    }

    // 보여줄 아이템 개수만큼 View 생성
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_timer, viewGroup, false)

        return ViewHolder(view)
    }

    // 생성된 View에 보여줄 데이터 결정
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.state.setBackgroundColor(Color.rgb(0,0,0))

        val time = timerList[position].time
        val date = timerList[position].date
        val day = timerList[position].day
        val dateDay = if (date != "null") timerList[position].date else day
        val state = timerList[position].on == "1"
        val turningOn = timerList[position].turningOn == "1"
        val actionText =
            if (turningOn) viewHolder.context.getString(R.string.turningOn)
            else viewHolder.context.getString(R.string.turningOff)


        viewHolder.itemView.setOnClickListener {
            val dialog = TimerSettingDialog(viewHolder.context)
            dialog.setting(time, date, day, turningOn)
        }
    
        // 타이머 시간
        viewHolder.timeTextView.text = time
        // 타이머 날짜 또는 요일
        viewHolder.dateDayTextView.text = dateDay
        //타이머 on/off
        viewHolder.state.isChecked = state
        // 불을 끄는/ 켜는 동작
        viewHolder.actionTextView.text = actionText
    }

    // 보여줄 아이템이 몇 개인지
    override fun getItemCount() = timerList.size

    fun submitList(timerList: ArrayList<TimerItem>){
        this.timerList = timerList
    }
}