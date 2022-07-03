package com.asi.emotionmark.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asi.emotionmark.R
import com.asi.emotionmark.presentation.ui.mark_detail.MarkDetailFragment
import com.asi.emotionmark.presentation.ui.mark_list.MarksFragment
import java.util.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), MarksFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
//            val fragment = MarkDetailFragment()
            val fragment = MarksFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onMarkSelected(markId: UUID?) {
        val fragment = MarkDetailFragment.newInstance(markId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

}