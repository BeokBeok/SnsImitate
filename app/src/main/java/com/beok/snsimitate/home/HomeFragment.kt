package com.beok.snsimitate.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.beok.common.Constant
import com.beok.common.base.BaseAdapter
import com.beok.common.base.BaseFragment
import com.beok.snsimitate.BR
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.databinding.FragmentHomeBinding
import com.beok.snsimitate.detail.DetailActivity
import com.beok.snsimitate.home.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        showContent()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.selectedItem.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { item ->
                when (item) {
                    is User -> startUserDetailActivity(item)
                    is Card -> startCardDetailActivity(item)
                }
            }
        })
    }

    private fun startCardDetailActivity(item: Card) {
        startActivity(
            DetailActivity.newIntent(this@HomeFragment.activity, item.id, Constant.TYPE_CARD)
        )
    }

    private fun startUserDetailActivity(item: User) {
        startActivity(
            DetailActivity.newIntent(this@HomeFragment.activity, item.id, Constant.TYPE_USER)
        )
    }

    private fun setupRecyclerView() {
        binding.rvHomeCards.adapter = BaseAdapter<Card>(
            layoutBindingId = R.layout.recyclerview_home_cards to BR.item,
            viewModels = mapOf(BR.vm to viewModel)
        )
        binding.rvHomeUsers.adapter = BaseAdapter<User>(
            layoutBindingId = R.layout.recyclerview_home_users to BR.item,
            viewModels = mapOf(BR.vm to viewModel)
        )
    }

    private fun showContent() {
        viewModel.fetchHome()
    }

    private fun setupViewModel() {
        binding.vm = viewModel
    }
}
