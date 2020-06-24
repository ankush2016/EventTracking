package com.tools.eventtrackinglib.utility

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.tools.eventtrackinglib.R
import com.tools.eventtrackinglib.model.RatingModel
import kotlinx.android.synthetic.main.et_custom_alert_dialog_layout.view.*
import kotlinx.android.synthetic.main.et_thanks_dialog_layout.view.*


object Utils {

    fun userActionPerformed(context: Context, ratingModel: RatingModel) {
        val isAppRated = getBooleanFromSharedPrefs(context, ETConstants.APP_RATED_KEY, false, ETConstants.PREF_NAME)
        if (isAppRated) return
        val userActionPerformedCount = getIntFromSharedPrefs(context, ETConstants.USER_ACTION_PERFORMED_KEY, 0, ETConstants.PREF_NAME)
        saveIntInSharedPrefs(context, ETConstants.USER_ACTION_PERFORMED_KEY, userActionPerformedCount + 1, ETConstants.PREF_NAME)
        Log.e("ANKUSH", "userActionPerformedCount = ${userActionPerformedCount + 1}")
        if (ratingModel.rulesList.contains(userActionPerformedCount)) {
            //Show Rate Us
            showRateUsAlertDialog(context, ratingModel)
        }
    }

    private fun showRateUsAlertDialog(context: Context, ratingModel: RatingModel) {
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.et_custom_alert_dialog_layout, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
        ratingModel.headerText?.let { dialogView.tvQuestion.text = it }
        ratingModel.descriptionText?.let { dialogView.tvSubDescription.text = it }
        dialogView.tvRating1.setOnClickListener { showLowRatingToast(context, alertDialog) }
        dialogView.tvRating2.setOnClickListener { showLowRatingToast(context, alertDialog) }
        dialogView.tvRating3.setOnClickListener { showLowRatingToast(context, alertDialog) }
        dialogView.tvRating4.setOnClickListener { showThanksDialog(context, alertDialog, ratingModel) }
        dialogView.tvRating5.setOnClickListener { showThanksDialog(context, alertDialog, ratingModel) }
    }

    private fun showLowRatingToast(context: Context, alertDialog: AlertDialog) {
        alertDialog.dismiss()
        Toast.makeText(context, "Thanks for the feedback!", Toast.LENGTH_SHORT).show()
    }

    private fun showThanksDialog(context: Context, firstAlertDialog: AlertDialog, ratingModel: RatingModel) {
        firstAlertDialog.dismiss()
        val dialogView: View = LayoutInflater.from(context).inflate(R.layout.et_thanks_dialog_layout, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

        ratingModel.thanksDialogTitle?.let { dialogView.tvThanksTitle.text = it }
        ratingModel.thanksDialogSubtitle?.let { dialogView.tvThanksSubtitle.text = it }
        dialogView.tvCancel.setOnClickListener { alertDialog.dismiss() }
        dialogView.tvRateApp.setOnClickListener {
            alertDialog.dismiss()
            saveBooleanInSharedPrefs(context, ETConstants.APP_RATED_KEY, true, ETConstants.PREF_NAME)
            val appPackageName = ratingModel.applicationId
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (ange: ActivityNotFoundException) {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
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

    fun saveBooleanInSharedPrefs(context: Context, key: String, value: Boolean, prefName: String) {
        val editor: SharedPreferences.Editor = context.getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanFromSharedPrefs(context: Context, key: String, defaultValue: Boolean, prefName: String): Boolean {
        val prefs: SharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return prefs.getBoolean(key, defaultValue)
    }
}