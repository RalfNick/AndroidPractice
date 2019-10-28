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
class MyLayout1 @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        const val TAG = "MyLayout - 1"
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // (1)自己消耗事件
//        return true
        // (2)自己不消耗事件，回溯到父 View 或者 Activity 中的 onTouchEvent
//        return false
        // (3)自己不消耗，传给自己的 onTouchEvent
        return super.dispatchTouchEvent(ev)
    }

    /**
     * return super.onInterceptTouchEvent(ev) 和 return false 的效果是一样的，不拦截事件，事件会传递到子 View
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        Log.e(MyLayout1.TAG, "--- > onInterceptTouchEvent -- ${convertActivon2Name(ev.action)}")
        var result = false
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                // (1)false，不拦截事件，事件会传递到子 View
                // a. 如果子 View 拦截 down 事件（可点击或者在子 view 的 onTouchEvent 中返回 true）
                // 则后续的 move、up 等事件都将先传递到 ViewGroup 的 onInterceptTouchEvent 的方法
                // b. 如果没有子 View 拦截 down 事件
                // 则 down 事件将交由该 ViewGroup 的 onTouchEvent 来处理，后续事件都不再走 onInterceptTouchEvent
                // (2)true，事件拦截，事件会交给自己的 onTouchEvent 处理，
                // 无论 onTouchEvent 是否返回 true 都不再走 onInterceptTouchEvent，并且事件不会再向下传递给子 View
                result = false
            }
            MotionEvent.ACTION_MOVE -> {
                // (3)只有上面的 down 事件中为 false 时，才有可能走这里。有可能的原因：看子 view 的处理，有子 View 并且子 View 拦截 down 事件
                // a. false 不拦截，move 事件会流向子 View，子 View 中 onTouchEvent 中为 true，
                // 则后续的 move、up 等事件都将先传递到 ViewGroup 的 onInterceptTouchEvent 的方法
                // 子 View 中 onTouchEvent 中为 false,事件会回溯到 MyLayout1 父 View 或者 Activity 中的 onTouchEvent，MyLayout1 不接收，MyLayout1 只接收来自 onInterceptTouchEvent 拦截的事件
                // b. true 拦截，move 事件交给自己的 onTouchEvent 来处理, 后续 move 和 up 事件不经过 onInterceptTouchEvent，直接传给 MyLayout1 的 onTouchEvent，
                // move 和 up 的返回值影响父 View 或者 Activity 能否收到响应的事件
                result = false
            }
            MotionEvent.ACTION_UP -> {
                // (4) 只有 onInterceptTouchEvent 中 move 不拦截，
                // 并且子 View 中 onTouchEvent 中 down 事件为 true，up 事件才会走这里
                // a. true，事件【不会】传递到 MyLayout1 的 onTouchEvent，子 View 的 dispatchTouchEvent 中收到 ACTION_CANCEL,
                // MyLayout1 的 父 View 或者 Activity 会收到 up 事件
                // b. false 交给子 view 处理
                result = false
            }
        }
        return result
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        var result = false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(MyLayout1.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                // onInterceptTouchEvent 拦截 down 事件或者事件从子 view 回溯回来：
                // (1) true，后续事件 move 和 up 直接传到这里，不再通过 onInterceptTouchEvent
                // (2) false，事件会回溯到父 View 或者 Activity,后续事件不再传给到 MyLayout1
                result = false
            }
            /**
             * down 事件返回值直接影响 move 和 up，down 为 false，
             * 后续事件都会收不到，为 true，会收到 move 和 up，move 事件中的返回值不会影响 up 事件的接收
             */
            MotionEvent.ACTION_MOVE -> {
                Log.e(MyLayout1.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                // 能到这里有 2 种情况
                // a. 由自己的 onTouchEvent 中 down 事件为 true,后续事会直接传到这里
                // b. onInterceptTouchEvent 不拦截 down 事件，子 View 拦截 down 事件，onInterceptTouchEvent 中拦截 move 事件
                // true 和 false 只影响 move 能否到达父 View 或者 Activity，不影响 up 事件的接收
                result = false
            }
            MotionEvent.ACTION_UP -> {
                Log.e(MyLayout1.TAG, "--- > onTouchEvent -- ${convertActivon2Name(event.action)}")
                // 能到这里只有一种情况
                // a.由自己的 onTouchEvent 中 down 事件为 true
                result = false
            }
        }
        return result
    }
}