package com.ariefzuhri.suitmediainterntest.common.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

open class BaseDialog : DialogFragment() {

    val isCreated get() = dialog != null
    val isShowing get() = dialog?.isShowing ?: false

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }
}