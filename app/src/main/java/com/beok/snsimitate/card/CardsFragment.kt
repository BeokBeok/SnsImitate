package com.beok.snsimitate.card

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import com.beok.common.base.BaseFragment
import com.beok.common.base.BasePagingAdapter
import com.beok.snsimitate.BR
import com.beok.snsimitate.R
import com.beok.snsimitate.card.model.Card
import com.beok.snsimitate.databinding.FragmentCardsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : BaseFragment<FragmentCardsBinding>(R.layout.fragment_cards) {

    private val viewModel by viewModels<CardsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.cards.observe(viewLifecycleOwner, {
            @Suppress("UNCHECKED_CAST")
            (binding.rvCardsContent.adapter as BasePagingAdapter<Card>).submitList(it)
        })
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

    private fun setupViewModel() {
        binding.vm = viewModel
    }
}
