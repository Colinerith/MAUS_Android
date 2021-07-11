package com.example.maus.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.maus.R
import com.example.maus.data.TimerItem

class TimerRecyclerViewAdapter(private var timerList: ArrayList<TimerItem>) :
    RecyclerView.Adapter<TimerRecyclerViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    //private var timerList = ArrayList<TimerItem>()

    // ViewHolder단위 객체로 View의 데이터 설정
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var timeTextView: TextView
        var dateTextView: TextView
        var onoffSwitch: Switch

        init {
            // Define click listener for the ViewHolder's View.
            timeTextView = view.findViewById(R.id.timeTextView)
            dateTextView = view.findViewById(R.id.dateTextView)
            onoffSwitch = view.findViewById(R.id.onoffSwitch)
        }
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.timeTextView.text = timerList[position].time
        if(timerList[position].date != "null")
            viewHolder.dateTextView.text = timerList[position].date
        else
            viewHolder.dateTextView.text = timerList[position].day
        viewHolder.onoffSwitch.isChecked = timerList[position].on == "1"
        Log.d("adapter: ", timerList[position].toString())
//        viewHolder.apply{
//
//        }
    }

    // 보여줄 아이템이 몇 개인지
    override fun getItemCount() = timerList.size

    fun submitList(timerList: ArrayList<TimerItem>){
        this.timerList = timerList
    }

}