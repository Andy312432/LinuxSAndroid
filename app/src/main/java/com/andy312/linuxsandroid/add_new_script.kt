package com.andy312.linuxsandroid

import android.util.Log
import com.topjohnwu.superuser.Shell
class add_new_script {
    companion object {
        fun requestRoot(): Boolean {
            Shell.cmd("su").exec()
            val rootStatus: Boolean? = Shell.isAppGrantedRoot()
            Log.d("basic", "Root status: $rootStatus")
            return rootStatus == true
        }
    }

}