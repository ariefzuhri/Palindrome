package com.ariefzuhri.suitmediainterntest.ui.second

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.ariefzuhri.suitmediainterntest.R
import com.ariefzuhri.suitmediainterntest.common.util.EXTRA_NAME
import com.ariefzuhri.suitmediainterntest.common.util.EXTRA_USER
import com.ariefzuhri.suitmediainterntest.databinding.ActivitySecondBinding
import com.ariefzuhri.suitmediainterntest.common.action.goToThird
import com.ariefzuhri.suitmediainterntest.common.base.BaseActivity
import com.ariefzuhri.suitmediainterntest.data.model.User

class SecondActivity : BaseActivity() {

    private lateinit var binding: ActivitySecondBinding

    private lateinit var name: String
    private var selectedUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getExtras()

        initView()
        initClickListeners()
    }

    private fun getExtras() {
        intent.extras?.let {
            name = it.getString(EXTRA_NAME)!!
        }
    }

    private fun initView() {
        binding.apply {
            tbSecond.init()
            tvName.text = name
        }
    }

    private fun initClickListeners() {
        binding.apply {
            btnChooseUser.setOnClickListener {
                goToThird(thirdResultLauncher)
            }
        }
    }

    private val thirdResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                selectedUser = result.data?.getParcelableExtra(EXTRA_USER)
                setSelectedUserName()
            }
        }

    private fun setSelectedUserName() {
        val selectedUserName = selectedUser?.name
        binding.tvSelectedUserName.text =
            if (selectedUserName.isNullOrEmpty()) getString(R.string.tv_selected_user_name_second)
            else selectedUserName
    }
}