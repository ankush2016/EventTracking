package com.tools.eventtrackinglib.utility

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.tools.eventtrackinglib.R


object Utils {

    fun userActionPerformed(context: Context, rulesList: ArrayList<Int>) {
        val userActionPerformedCount = getIntFromSharedPrefs(context, ETConstants.USER_ACTION_PERFORMED_KEY, 0, ETConstants.PREF_NAME)
        saveIntInSharedPrefs(context, ETConstants.USER_ACTION_PERFORMED_KEY, userActionPerformedCount + 1, ETConstants.PREF_NAME)
        Log.e("ANKUSH", "userActionPerformedCount = ${userActionPerformedCount + 1}")
        if (rulesList.contains(userActionPerformedCount)) {
            //Show Rate Us
            showRateUsAlertDialog(context)
        }
    }

    private fun showRateUsAlertDialog(context: Context) {
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.et_custom_alert_dialog_layout, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    fun saveIntInSharedPrefs(context: Context, key: String, value: Int, prefName: String) {
        val editor: SharedPreferences.Editor = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getIntFromSharedPrefs(context: Context, key: String, defaultValue: Int, prefName: String): Int {
        val prefs: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getInt(key, defaultValue)
    }
}