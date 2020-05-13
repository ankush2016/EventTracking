package com.tools.eventtrackinglib

import android.util.Log

class MyLogger {
    companion object {
        fun d(tag: String, message: String) {
            Log.d(tag, message)
        }
    }
}