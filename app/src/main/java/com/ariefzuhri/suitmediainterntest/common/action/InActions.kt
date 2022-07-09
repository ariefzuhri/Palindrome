package com.ariefzuhri.suitmediainterntest.common.action

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentManager
import com.ariefzuhri.suitmediainterntest.common.util.EXTRA_NAME
import com.ariefzuhri.suitmediainterntest.common.view.dialog.LoadingDialog
import com.ariefzuhri.suitmediainterntest.ui.second.SecondActivity
import com.ariefzuhri.suitmediainterntest.ui.third.ThirdActivity

fun Context?.goToSecond(name: String?) {
    this?.let { context ->
        val intent = Intent(context, SecondActivity::class.java).also { intent ->
            intent.putExtra(EXTRA_NAME, name)
        }
        context.startActivity(intent)
    }
}

fun Context?.goToThird(resultLauncher: ActivityResultLauncher<Intent>?) {
    this?.let { context ->
        val intent = Intent(context, ThirdActivity::class.java)
        resultLauncher?.launch(intent)
    }
}

fun LoadingDialog.show(fragmentManager: FragmentManager) {
    if (isCreated) {
        if (!isShowing) dialog?.show()
    } else {
        show(fragmentManager, LoadingDialog.TAG)
    }
}

fun LoadingDialog.close() {
    if (isCreated && isShowing) {
        dialog?.dismiss()
    }
}