package com.example.maus.ui.main
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.maus.R
import com.google.firebase.database.*
import kotlin.properties.Delegates

class SwitchFragment : Fragment() {
    private var database = FirebaseDatabase.getInstance()
    private var state by Delegates.notNull<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_switch, container, false)
        val switchBtn = root.findViewById<ImageView>(R.id.imageView)

        val ref : DatabaseReference = database.getReference("User/a")
        // 한 번만 읽기
//        ref.child("state").get().addOnSuccessListener {
//            Log.i("firebaseStart", "Got value ${it.value}")
//            state = it.value as String
//            if(state == "1")
//                switchBtn.setImageResource(R.drawable.on)
//            else
//                switchBtn.setImageResource(R.drawable.off)
//        }.addOnFailureListener{
//            Log.e("firebase", "Error getting data", it)
//        }

        // 영구 리스너로 이벤트 발생 시마다 읽기 (페이지 진입 시에도 읽음)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.value
                Log.i("firebase", "Got value2 $post")
                ref.child("state").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    state = it.value as String
                    // 스위치 이미지 변경
                    if(state == "1")
                        switchBtn.setImageResource(R.drawable.on)
                    else
                        switchBtn.setImageResource(R.drawable.off)
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(postListener)


        // 스위치 이미지 터치 시 이미지 전환 및 database state 변경
        switchBtn.setOnClickListener {
            if(state == "1"){
                state = "0"
                ref.child("state").setValue(state)
            }
            else {
                state = "1"
                ref.child("state").setValue(state)
            }
        }

        return root
    }
}