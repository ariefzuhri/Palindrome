package com.ariefzuhri.suitmediainterntest.common.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.ariefzuhri.suitmediainterntest.common.action.close
import com.ariefzuhri.suitmediainterntest.common.action.show
import com.ariefzuhri.suitmediainterntest.common.view.dialog.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy { LoadingDialog.newInstance() }

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

    fun startLoading() {
        loadingDialog.show(supportFragmentManager)
    }

    fun stopLoading() {
        loadingDialog.close()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}