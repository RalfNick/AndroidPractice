package com.ralf.vieweventtest1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_view_group_view.*

class ViewGroupAndViewActivity : AppCompatActivity() {

    companion object {
        const val TAG = "VGAndViewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_group_view)
        myview1.setOnTouchListener { v, event ->
            Log.e("myview1","--- > setOnTouchListener -- ${convertActivon2Name(event.action)}")
            return@setOnTouchListener super.onTouchEvent(event)
        }
        linear_layout.setOnTouchListener{ v, event ->
            Log.e("linear_layout","--- > setOnTouchListener -- ${convertActivon2Name(event.action)}")
            return@setOnTouchListener super.onTouchEvent(event)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }

        }
        return super.onTouchEvent(event)
    }
}
