package com.example.maus.ui.main
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.LayoutInflater.from
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.maus.R

class SwitchFragment2 : Fragment() {
//    private lateinit var profileViewModel: ProfileViewModel
//    private lateinit var pToolbar: androidx.appcompat.widget.Toolbar
//    private var nick: String = ""
//    private var hei: String = ""
//    private var wei: String = ""
//    private var pic: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_switch, container, false)
        //pToolbar = root.findViewById(R.id.profile_toolbar)
       // (activity as AppCompatActivity).setSupportActionBar(pToolbar)

        var on:Boolean = false
        val switchBtn = root.findViewById<ImageView>(R.id.imageView)
        switchBtn.setOnClickListener {
            //Toast.makeText(this.context, "ㅇㅇ", Toast.LENGTH_SHORT).show();
            if(on)
                switchBtn.setImageResource(R.drawable.off)
            else
                switchBtn.setImageResource(R.drawable.on)
            on = !on
        }

        return root
    }
}