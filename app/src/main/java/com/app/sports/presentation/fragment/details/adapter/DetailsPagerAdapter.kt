package com.app.sports.presentation.fragment.details.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.sports.presentation.fragment.details.SeasonsFragment
import com.app.sports.presentation.fragment.details.TeamsFragment

class DetailsPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) SeasonsFragment()
        else TeamsFragment()
    }
}
