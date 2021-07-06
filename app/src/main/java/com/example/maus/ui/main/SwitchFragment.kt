package com.example.maus.ui.main
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.maus.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SwitchFragment : Fragment() {
    private var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            val root = inflater.inflate(R.layout.fragment_switch, container, false)
            val ref : DatabaseReference = database.getReference("User/a")
            ref.child("state").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
            }.addOnFailureListener{
                Log.e("firebase", "Error getting data", it)
            }

            var on = false
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