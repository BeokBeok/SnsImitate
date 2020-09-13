package com.beok.snsimitate.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.beok.common.base.BaseActivity
import com.beok.common.base.BaseAdapter
import com.beok.snsimitate.BR
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        showContent()
    }

    private fun setupRecyclerView() {
        binding.rvDetailRecommendCard.adapter = BaseAdapter<Card>(
            layoutBindingId = R.layout.recyclerview_recommend_card to BR.item,
            viewModels = mapOf(BR.vm to viewModel)
        )
    }

    private fun showContent() {
        viewModel.fetchCardDetail(intent.getIntExtra(CARD_ID, -1).toString())
    }

    private fun setupViewModel() {
        binding.vm = viewModel
    }

    companion object {
        private const val CARD_ID = "card_id"

        fun newIntent(context: Context?, cardId: Int) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(CARD_ID, cardId)
            }
    }
}
