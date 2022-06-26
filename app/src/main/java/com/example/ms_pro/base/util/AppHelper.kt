package com.example.ms_pro.base.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.widget.EditText
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.KeyboardUtils.showSoftInput
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.TimeUtils
import com.example.ms_pro.R
import com.example.ms_pro.base.compresstor.Compressor
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by admin on 17/1/2018.
 */
object AppHelper {
    @JvmStatic
    @SuppressLint("SimpleDateFormat")
    fun formatLongToTime(timeLong: Long?): String {
        return SimpleDateFormat("dd/MM/yyyy").format(Date(timeLong!!))
    }

    @JvmStatic
    fun formatDuration(second: Double): String {
        if (second == 0.0) {
            val durationFormat = StringUtils.getString(R.string.duration_minutes)
            return String.format(durationFormat, 0)
        }
        return if (second < 60) {
            val durationFormat = StringUtils.getString(R.string.duration_minutes)
            String.format(durationFormat, 1)
        } else if (second > 60 && second < 60 * 60) {
            val durationFormat = StringUtils.getString(R.string.duration_minutes)
            String.format(durationFormat, doubleToLong(second / 60))
        } else {
            val durationFormat: String
            if (second % 3600 != 0.0) {
                durationFormat = StringUtils.getString(R.string.duration_hour_minutes)
                String.format(
                    durationFormat,
                    doubleToLong(second / 3600),
                    doubleToLong(second % 3600 / 60)
                )
            } else {
                durationFormat = StringUtils.getString(R.string.duration_hour)
                String.format(durationFormat, doubleToLong(second / 3600))
            }
        }
    }

    @JvmStatic
    fun formatDistance(meter: Double): String {
        return if (meter < 1000) {
            val durationFormat = StringUtils.getString(R.string.duration_metter)
            String.format(durationFormat, doubleToLong(meter * 1.0))
        } else {
            val durationFormat = StringUtils.getString(R.string.duration_kilommetter)
            val rs = meter / 1000
            val distance: String =
                if (rs - rs.toInt() > 0.05) String.format("%.1f", rs) else rs.toInt().toString()
            String.format(durationFormat, distance).replace(",", ".")
        }
    }

    @JvmStatic
    fun formatDistance(km: Long): String {
        val durationFormat = StringUtils.getString(R.string.duration_kilommetter)
        return String.format(durationFormat, km.toString()).replace(",", ".")
    }

    @JvmStatic
    fun formatArrivalTime(duration: Double): String {
        return TimeUtils.millis2String(Math.round(duration), "HH:mm")
    }

    @JvmStatic
    fun doubleToLong(number: Double): Long {
        val tmp: Long
        tmp = (number * 1.0).toLong()
        return tmp
    }

    @JvmStatic
    fun calFinishTime(duration: Double): String {
        val dateFormat = SimpleDateFormat("HH:mm")
        val time = System.currentTimeMillis() + duration * 1000
        return dateFormat.format(Date(doubleToLong(time)))
    }

    @JvmStatic
    fun showToastMessage(context: Context?, message: String?) {
        ToastUtil.toastNormal(context, message)
    }

    @JvmStatic
    fun hideKeyboard(context: Activity) {
        KeyboardUtils.hideSoftInput(context);
    }

    @JvmStatic
    fun showKeyboard(activity: Activity, editText: EditText) {
        KeyboardUtils.showSoftInput(editText)
    }

}