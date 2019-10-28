package com.ralf.vieweventtest1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyLayout1
 * @email -
 * @date 2019/09/09 20:32
 **/
class MyLayout2 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "Layout - 2"
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "--- > dispatchTouchEvent --- > ${convertActivon2Name(ev.action)}")
        // (1)自己消耗事件
//        return true
        // (2)自己不消耗事件，回溯到父 View 中的 onTouchEvent
//        return false
        // (3)自己不消耗，传给自己的 onTouchEvent
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e(TAG, "--- > onInterceptTouchEvent -- ${convertActivon2Name(ev.action)}")
        var result = false
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                result = false
            }
            MotionEvent.ACTION_MOVE -> {
                result = false
            }
            MotionEvent.ACTION_UP -> {
                result = false
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        var result = false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                result = false
            }

            MotionEvent.ACTION_MOVE -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                result = false
            }
            MotionEvent.ACTION_UP -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                result = false
            }
        }
        return result
    }
}