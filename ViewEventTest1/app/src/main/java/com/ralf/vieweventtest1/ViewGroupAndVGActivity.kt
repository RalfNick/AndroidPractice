package com.ralf.vieweventtest1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

class ViewGroupAndVGActivity : AppCompatActivity() {


    companion object {
        const val TAG = "ViewGroupAndVGActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_group_and_vg)
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
