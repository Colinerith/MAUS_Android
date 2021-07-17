package com.example.maus.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.maus.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SettingFragment : Fragment() {
    private var database = FirebaseDatabase.getInstance()
    private lateinit var state:String
    private var path = "User/a"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        val emailTextView = root.findViewById<TextView>(R.id.email)
        val wifiNameTextView = root.findViewById<TextView>(R.id.wifiName)
        val wifiPwTextView = root.findViewById<TextView>(R.id.wifiPw)
        val howToUseTextView = root.findViewById<TextView>(R.id.howToUse)
        val locationTextView = root.findViewById<TextView>(R.id.location)


        howToUseTextView.setOnClickListener {
            val dialog =
        }
        return root
    }
}