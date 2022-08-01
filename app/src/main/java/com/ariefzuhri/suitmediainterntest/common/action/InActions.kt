package com.ariefzuhri.suitmediainterntest.common.action

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.ariefzuhri.suitmediainterntest.common.util.EXTRA_NAME
import com.ariefzuhri.suitmediainterntest.ui.second.SecondActivity
import com.ariefzuhri.suitmediainterntest.ui.third.ThirdActivity

fun Context?.goToSecond(name: String?) {
    this?.let { context ->
        Intent(context, SecondActivity::class.java).apply {
            putExtra(EXTRA_NAME, name)
            context.startActivity(this)
        }
    }
}

fun Context?.goToThird(resultLauncher: ActivityResultLauncher<Intent>?) {
    this?.let { context ->
        Intent(context, ThirdActivity::class.java).apply {
            resultLauncher?.launch(this)
        }
    }
}