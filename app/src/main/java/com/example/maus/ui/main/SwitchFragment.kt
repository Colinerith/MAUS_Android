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
    private lateinit var state:String
    private var initialized = false // state값 초기화 이후에 사용할 수 있도록
    private var path = "User/a"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_switch, container, false)
        val switchBtn = root.findViewById<ImageView>(R.id.imageView)
        val ref : DatabaseReference = database.getReference(path)

//        // 한 번만 읽기
//        readOnce(ref, switchBtn)

        // 영구 리스너로 이벤트 발생 시마다 읽기 (페이지 진입 시에도 읽음)
        setListener(ref, switchBtn)

        // 스위치 이미지 터치 시  database state 변경
        switchBtn.setOnClickListener {
            if(initialized) // state 초기화 전까지는 상태 변경 불가
                firebaseStateToggle(ref)
        }

        return root
    }

    // 페이지 진입 시 state값 한 번 읽기
    private fun readOnce(ref : DatabaseReference, switchBtn:ImageView){
        ref.child("state").get().addOnSuccessListener {
            Log.i("firebaseStart", "Got value ${it.value}")
            state = it.value as String
            initialized = true
            switchImageToggle(switchBtn)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    // 리스너 등록
    private fun setListener(ref : DatabaseReference, switchBtn:ImageView){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val post = dataSnapshot.value
                Log.i("firebase", "Got value2 $post")
                ref.child("state").get().addOnSuccessListener {
                    Log.i("firebase", "Got value ${it.value}")
                    state = it.value as String
                    initialized = true
                    // 스위치 이미지 변경
                    switchImageToggle(switchBtn)
                }.addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("firebase", "loadPost:onCancelled", databaseError.toException())
            }
        }
        ref.addValueEventListener(postListener)
    }

    // 스위치의 이미지 변경
    private fun switchImageToggle(switchBtn:ImageView){
        if(state == "1")
            switchBtn.setImageResource(R.drawable.switch_on_gray)
        else
            switchBtn.setImageResource(R.drawable.switch_off_gray)
    }
    
    // database state 변경
    private fun firebaseStateToggle(ref : DatabaseReference){
        if(state == "1"){
            state = "0"
            ref.child("state").setValue(state)
        }
        else {
            state = "1"
            ref.child("state").setValue(state)
        }
    }
}