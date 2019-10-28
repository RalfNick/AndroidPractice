package com.ralf.vieweventtest1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent

class EventTransmitActivity : AppCompatActivity() {

    companion object {
        const val TAG = "EventTransmitActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_transmit)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(EventTransmitActivity.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.e(EventTransmitActivity.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }
            MotionEvent.ACTION_UP -> {
                Log.e(EventTransmitActivity.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
            }

        }
        return super.onTouchEvent(event)
    }

}
