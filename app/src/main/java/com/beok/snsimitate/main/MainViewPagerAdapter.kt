package com.beok.snsimitate.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.beok.snsimitate.card.CardsFragment
import com.beok.snsimitate.home.HomeFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = FRAGMENT_COUNT

    override fun createFragment(position: Int): Fragment =
        when (position) {
            1 -> CardsFragment()
            else -> HomeFragment()
        }

    companion object {
        private const val FRAGMENT_COUNT = 2
    }
}
