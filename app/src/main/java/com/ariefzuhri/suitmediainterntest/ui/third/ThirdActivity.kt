package com.ariefzuhri.suitmediainterntest.ui.third

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariefzuhri.suitmediainterntest.common.base.BaseActivity
import com.ariefzuhri.suitmediainterntest.common.util.EXTRA_USER
import com.ariefzuhri.suitmediainterntest.common.view.adapter.LoadingStateAdapter
import com.ariefzuhri.suitmediainterntest.common.view.adapter.UserAdapter
import com.ariefzuhri.suitmediainterntest.common.view.viewmodel.ViewModelFactory
import com.ariefzuhri.suitmediainterntest.data.model.User
import com.ariefzuhri.suitmediainterntest.databinding.ActivityThirdBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdActivity : BaseActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: ThirdViewModel

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        initUserRecyclerView()
        populateUserAdapter()

        initView()
        initUserSwipeRefresh()
    }

    private fun initUserRecyclerView() {
        binding.rvUser.apply {
            val linearLayoutManager = LinearLayoutManager(this@ThirdActivity)
            layoutManager = linearLayoutManager

            userAdapter = UserAdapter(object : UserAdapter.EventListener {
                override fun onItemClicked(user: User) {
                    selectUser(user)
                }

                private fun selectUser(user: User) {
                    Intent().also { intent ->
                        intent.putExtra(EXTRA_USER, user)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
            })
            adapter = userAdapter
            adapter = userAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter { userAdapter.retry() },
                footer = LoadingStateAdapter { userAdapter.retry() }
            )

            setHasFixedSize(true)
        }
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory).get(ThirdViewModel::class.java)
    }

    private fun populateUserAdapter() {
        lifecycleScope.launch {
            viewModel.users.collectLatest { pagingData ->
                userAdapter.submitData(pagingData)
            }
        }
    }

    private fun initView() {
        binding.apply {
            tbThird.init()
        }
    }

    private fun initUserSwipeRefresh() {
        binding.srUser.apply {
            setOnRefreshListener {
                userAdapter.retry()
                isRefreshing = false
            }
        }
    }
}