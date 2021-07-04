package com.example.maus

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.maus.ui.main.SectionsPagerAdapter
import com.example.maus.ui.main.SwitchFragment2

class MainActivity : AppCompatActivity() {
    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime: Long = 0
    lateinit var myFragment: View
    lateinit var viewPagers: ViewPager
    lateinit var tabLayouts: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
//        sectionsPagerAdapter.apply{
//
//        }
//        val adapter = PagerAdapter(supportFragmentManager)
//        adapter.addFragment(MainFragment(), "메인")
//        adapter.addFragment(SearchFragment(), "검색")
//        adapter.addFragment(LibraryFragment(), "내 서재")

        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
//
//        setSupportActionBar(findViewById(R.id.appBarLayout3))
//        supportActionBar?.elevation = 0f

    }
//
//    private fun setUpViewPager() {
//        viewPagers = view_pager
//        tabLayouts = tabs
//
//        var adapter = SectionsPagerAdapter(root)
//        adapter.addFragment(SwitchFragment2(), "SWITCH")
//        adapter.addFragment(AlarmFragment(), "ALARM")
//        adapter.addFragment(AlarmFragment(), "ALARM")
//
//        viewPagers!!.adapter = adapter
//        tabLayouts!!.setupWithViewPager(viewPagers)
//    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0) {
            val tempTime = System.currentTimeMillis();
            val intervalTime = tempTime - backPressedTime;
            if (intervalTime in 0..FINISH_INTERVAL_TIME) {
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(this, "버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return
            }
        }
        super.onBackPressed();
    }
}