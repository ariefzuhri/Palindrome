package com.ariefzuhri.suitmediainterntest.common.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preventKeyboardFromPushingViewUp()
    }

    private fun preventKeyboardFromPushingViewUp() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    fun Toolbar?.init() {
        setSupportActionBar(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}