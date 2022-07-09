package com.ariefzuhri.suitmediainterntest.common.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ariefzuhri.suitmediainterntest.common.base.BaseDialog
import com.ariefzuhri.suitmediainterntest.databinding.DialogLoadingBinding

class LoadingDialog : BaseDialog() {

    private var _binding: DialogLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        val TAG = LoadingDialog::class.java.simpleName.toString()

        fun newInstance(): LoadingDialog {
            return LoadingDialog()
        }
    }
}