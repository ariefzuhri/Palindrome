package com.ariefzuhri.suitmediainterntest.ui.first

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ariefzuhri.suitmediainterntest.R
import com.ariefzuhri.suitmediainterntest.databinding.ActivityFirstBinding
import com.ariefzuhri.suitmediainterntest.common.util.getInput
import com.ariefzuhri.suitmediainterntest.common.action.goToSecond
import com.ariefzuhri.suitmediainterntest.common.base.BaseActivity
import com.ariefzuhri.suitmediainterntest.common.util.isPalindrome

class FirstActivity : BaseActivity() {

    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickListeners()
    }

    private fun initClickListeners() {
        binding.apply {
            btnCheck.setOnClickListener {
                checkPalindrome()
            }
            btnNext.setOnClickListener {
                nextScreen()
            }
        }
    }

    private fun checkPalindrome() {
        binding.edtPalindrome.apply {
            val text = getInput()
            if (text.isEmpty()) {
                error = context.getString(R.string.edt_error_palindrome_first)
                requestFocus()
            } else {
                error = null
                showPalindromeCheckingResult(
                    message =
                    if (text.isPalindrome()) getString(R.string.dialog_message_result_palindrome_first)
                    else getString(R.string.dialog_message_result_not_palindrome_first)
                )
            }
        }
    }

    private fun showPalindromeCheckingResult(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun nextScreen() {
        binding.edtName.apply {
            val name = binding.edtName.getInput()
            if (name.isEmpty()) {
                error = context.getString(R.string.edt_error_name_first)
                requestFocus()
            } else {
                error = null
                goToSecond(name)
            }
        }
    }
}