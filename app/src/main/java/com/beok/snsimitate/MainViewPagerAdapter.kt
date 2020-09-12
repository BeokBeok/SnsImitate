package com.beok.snsimitate

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.beok.snsimitate.card.CardsFragment
import com.beok.snsimitate.home.HomeFragment

class MainViewPagerAdapter(fm: FragmentManager, private val tabTitles: List<String>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = FRAGMENT_COUNT

    override fun getItem(position: Int): Fragment =
        when (position) {
            1 -> CardsFragment()
            else -> HomeFragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = tabTitles[position]

    companion object {
        private const val FRAGMENT_COUNT = 2
    }
}
