package com.ralf.vieweventtest1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyView1
 * @email -
 * @date 2019/09/09 20:39
 **/
class MyView1 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "MyView1"
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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var result = false
        when (event.action) {
            // 事件为 false，后续事件都不会传递给 MyView1，ACTION_MOVE 和 ACTION_UP 中 true 或 false 不影响事件传过来
            // 所以 ACTION_DOWN 事件才是决定的关键,起到导流的作用
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                result = true
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
        /**
         * (1)自己消耗事件
         * return true
         *
         * (2)自己不消耗事件，回溯到父 View 中的 onTouchEvent
         * return false
         *
         * (3)自己不消耗，回溯到父 View 中的 onTouchEvent
         * return super.onTouchEvent(event)
         */

    }
}