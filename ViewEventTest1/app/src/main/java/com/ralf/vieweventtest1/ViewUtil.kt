package com.ralf.vieweventtest1

import android.view.MotionEvent

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ViewUtil
 * @email -
 * @date 2019/09/09 21:18
 **/

fun convertActivon2Name(action: Int): String {
    var actionName = ""
    when (action) {
        MotionEvent.ACTION_DOWN -> {
            actionName = "ACTION_DOWN"
        }
        MotionEvent.ACTION_MOVE -> {
            actionName = "ACTION_MOVE"
        }
        MotionEvent.ACTION_UP -> {
            actionName = "ACTION_UP"
        }
        MotionEvent.ACTION_CANCEL -> {
            actionName = "ACTION_CANCEL"
        }
    }
    return actionName
}