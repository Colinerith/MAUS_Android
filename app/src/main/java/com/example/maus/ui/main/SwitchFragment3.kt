package com.example.maus.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.maus.R

// TODO: Rename parameter arguments, choose names that match
 //the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SwitchFragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class SwitchFragment3 : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_switch, container, false)

        val switchBtn = root.findViewById<ImageView>(R.id.imageView)
        switchBtn.setOnClickListener {
            Toast.makeText(this.context, "ㅇㅇ", Toast.LENGTH_SHORT).show();
            switchBtn.setImageResource(R.drawable.switch_on_350)
        }

        val btn = root.findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            Toast.makeText(this.context, "ㅇㅇ", Toast.LENGTH_SHORT).show();
            switchBtn.setImageResource(R.drawable.switch_on_350)
        }

        //return inflater.inflate(R.layout.fragment_switch, container, false)
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SwitchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwitchFragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}