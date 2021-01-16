package com.example.datapegawai.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.datapegawai.R
import org.aviran.cookiebar2.CookieBar

class Utils {
    companion object {
        fun errorToast(ctx: Context, msg: String, dur : Long = 2000) {

//            Utils.errorToast(ctx, msg)

            CookieBar.build(ctx as Activity)
                .setTitle("Gagal").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(android.R.color.holo_red_light)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }

        fun warningToast(ctx: Context, msg: String, dur : Long = 2000) {

//            Utils.warningToast(ctx, msg)
            CookieBar.build(ctx as Activity)
                .setTitle("Peringatan").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(R.color.yellow_warning)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }


        fun okToast(ctx: Context, msg: String, dur : Long = 2000){

            CookieBar.build(ctx as Activity)
                .setTitle("Sukses").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(R.color.green_success)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }


        fun infoToast(ctx: Context, msg: String, dur : Long = 2000){

            CookieBar.build(ctx as Activity)
                .setTitle("InformaSI").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(R.color.blue_info)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }
        fun hideSoftKeyboard(activity: Activity) {
            try {
                val inputMethodManager = activity.getSystemService(
                    Activity.INPUT_METHOD_SERVICE
                ) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken, 0
                )
            } catch (e: Exception) {
            }
        }
    }
}