package com.example.maus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
//import androidx.navigation.NavController
//import androidx.navigation.fragment.findNavController
import com.example.maus.R
import com.google.firebase.database.FirebaseDatabase
import kotlin.properties.Delegates

class TimerFragment : Fragment() {
    private var database = FirebaseDatabase.getInstance()
    private var state by Delegates.notNull<String>()
    //lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_timer, container, false)

        val addBtn = root.findViewById<ImageButton>(R.id.addBtn)
        addBtn.setOnClickListener {
//            //navController.navigate(R.id.action_first2_to_second2, bundle)
//            findNavController().navigate(R.id.action_timerFragment_to_timerSettingFragment)

//            val mDialogView = LayoutInflater.from(activity)
//                .inflate(R.layout.fragment_timersetting, null)
//            val mBuilder = activity?.let { it1 ->
//                AlertDialog.Builder(it1)
//                    .setView(mDialogView)
//                .setTitle("Timer Setting")
//            }
//
////            mDialogView.findViewById<EditText>(R.id.editNickname).setText(nick)
////            mDialogView.findViewById<EditText>(R.id.editHeight).setText(hei)
//            val mAlertDialog = mBuilder?.show()
//
//            //취소 버튼
//            val cancelButton = mDialogView.findViewById<Button>(R.id.cancelBtn)
//            cancelButton.setOnClickListener {
//                mAlertDialog?.dismiss()
//            }

            val dialog = TimerSettingDialog(root.context)
            dialog.create()

        }


        return root
    }
}