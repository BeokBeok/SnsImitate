package com.beok.snsimitate.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.beok.common.Constant
import com.beok.common.base.BaseActivity
import com.beok.common.base.BaseAdapter
import com.beok.snsimitate.BR
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.databinding.ActivityDetailBinding
import com.beok.snsimitate.home.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(R.layout.activity_detail) {

    override val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        showContent()
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.selectedItem.observe(this, {
            when (it) {
                is User -> {
                    if (!binding.gDetailCard.isVisible) return@observe
                    if (it.id == -1) return@observe
                    startActivity(newIntent(this, it.id, Constant.TYPE_USER))
                }
                is Card -> {
                    if (it.id == -1) return@observe
                    startActivity(newIntent(this, it.id, Constant.TYPE_CARD))
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.rvDetailRecommendCard.adapter = BaseAdapter<Card>(
            layoutBindingId = R.layout.recyclerview_recommend_card to BR.item,
            viewModels = mapOf(BR.vm to viewModel)
        )
    }

    private fun showContent() {
        when (intent.getStringExtra(TYPE)) {
            Constant.TYPE_USER -> {
                viewModel.fetchUserDetail(intent.getIntExtra(ID, -1).toString())
                binding.gDetailCard.isVisible = false
            }
            Constant.TYPE_CARD -> {
                viewModel.fetchCardDetail(intent.getIntExtra(ID, -1).toString())
                binding.tvDetailUserIntroduction.isVisible = false
            }
        }
    }

    private fun setupViewModel() {
        binding.vm = viewModel
    }

    companion object {
        private const val ID = "id"
        private const val TYPE = "type"

        fun newIntent(context: Context?, id: Int, type: String) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(ID, id)
                putExtra(TYPE, type)
            }
    }
}
