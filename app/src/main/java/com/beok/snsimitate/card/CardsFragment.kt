package com.beok.snsimitate.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import com.beok.common.Constant
import com.beok.common.base.BaseFragment
import com.beok.common.base.BasePagingAdapter
import com.beok.snsimitate.BR
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.databinding.FragmentCardsBinding
import com.beok.snsimitate.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : BaseFragment<FragmentCardsBinding>(R.layout.fragment_cards) {

    private val viewModel by viewModels<CardsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.cards.observe(viewLifecycleOwner, {
            @Suppress("UNCHECKED_CAST")
            (binding.rvCardsContent.adapter as BasePagingAdapter<Card>).submitList(it)
        })
        viewModel.selectedCard.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { card ->
                if (card.id == -1) return@observe
                startCardDetailActivity(card)
            }
        })
    }

    private fun startCardDetailActivity(card: Card) {
        startActivity(
            DetailActivity.newIntent(this@CardsFragment.activity, card.id, Constant.TYPE_CARD)
        )
    }

    private fun setupRecyclerView() {
        binding.rvCardsContent.adapter = BasePagingAdapter(
            layoutBindingId = R.layout.recyclerview_cards to BR.item,
            viewModels = mapOf(BR.vm to viewModel),
            diffUtil = object : DiffUtil.ItemCallback<Card>() {
                override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean =
                    oldItem.id == newItem.id
            }
        )
    }
}
