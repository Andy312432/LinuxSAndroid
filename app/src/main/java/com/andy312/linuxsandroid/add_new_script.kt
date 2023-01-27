package com.andy312.linuxsandroid

import android.util.Log

class add_new_script {
    companion object {
        fun requestRoot(): Boolean {
            //FIXME: How to detect we have root access correctly??
            try {
                Runtime.getRuntime().exec("su")
            }
            catch (e: InterruptedException) {
                Log.e("basic", "Root not granted")
                return false
            }
            Log.e("basic", "Root granted")
            return true
        }
    }

}