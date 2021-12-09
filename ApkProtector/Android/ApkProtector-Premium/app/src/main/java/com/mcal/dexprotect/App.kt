package com.mcal.dexprotect

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.mcal.dexprotect.data.Preferences
import com.mcal.dexprotect.task.Environment
import org.jetbrains.annotations.Nullable

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        Environment(context).init()
        if (Preferences.isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        private var preferences: SharedPreferences? = null

        @JvmStatic
        fun getContext(): Context? {
            if (context == null) {
                context = App()
            }
            return context
        }

        @JvmStatic
        @Nullable
        fun getPreferences(): SharedPreferences {
            if (preferences == null) {
                preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext())
            }
            return preferences!!
        }

        /**
         * Конвертация dpi в пиксели
         *
         * @param dp число DPI
         * @return число пикселей, эквивалентное DPI
         */
        @JvmStatic
        fun dpToPx(dp: Int): Int {
            val displayMetrics = getContext()!!.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }
    }
}