package com.demo.common_ui.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.demo.common_ui.R

object ProgressDialogUtil {
    fun setProgressDialog(context: Context): Dialog =
        Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.progress_indicator)
        }

}