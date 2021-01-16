package com.example.datapegawai.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.datapegawai.R
import org.aviran.cookiebar2.CookieBar
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object {
        var formatDateServerDateOnly = "yyyy-MM-dd"
        var formatDateCust = "dd MMMM yyyy"
        fun changeDateFormat(oldDateString: String):String{
            try {
                var newDateString= ""

                val sdf = SimpleDateFormat(formatDateServerDateOnly)
                val d = sdf.parse(oldDateString)
                sdf.applyPattern(formatDateCust)
                newDateString = sdf.format(d)
                return newDateString
            } catch (e: Exception) {
                return oldDateString
            }
        }

        fun errorToast(ctx: Context, msg: String, dur: Long = 2000) {

//            Utils.errorToast(ctx, msg)

            CookieBar.build(ctx as Activity)
                .setTitle("Gagal").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(android.R.color.holo_red_light)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }

        fun warningToast(ctx: Context, msg: String, dur: Long = 2000) {

//            Utils.warningToast(ctx, msg)
            CookieBar.build(ctx as Activity)
                .setTitle("Peringatan").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(R.color.yellow_warning)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }


        fun okToast(ctx: Context, msg: String, dur: Long = 2000){

            CookieBar.build(ctx as Activity)
                .setTitle("Sukses").setTitleColor(android.R.color.white)
                .setMessage(msg).setMessageColor(android.R.color.white)
                .setDuration(dur) // 5 seconds
                .setBackgroundColor(R.color.green_success)
                .setCookiePosition(CookieBar.BOTTOM)
                .show()
        }


        fun infoToast(ctx: Context, msg: String, dur: Long = 2000){

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

        fun datePickerUtils(context: Context?, getInter: InterfaceUmum.getDatePicker) {
            val datePicker: DatePickerDialog
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR] // current year
            val mMonth = c[Calendar.MONTH] // current month
            val mDay = c[Calendar.DAY_OF_MONTH] // current day
            datePicker = DatePickerDialog(
                context!!,
                { view, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar[year, monthOfYear] = dayOfMonth
                    val format =
                        SimpleDateFormat(formatDateServerDateOnly)
                    val dateString = format.format(calendar.time)
                    getInter.getTanggal(dateString)
                }, mYear, mMonth, mDay
            )
            datePicker.show()
        }
    }


}