package com.app.sports.presentation.fragment.details

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.app.sports.R
import com.app.sports.base.BaseFragment
import com.app.sports.databinding.FragmentLeagueDetailsBinding
import com.app.sports.presentation.MainViewModel
import com.app.sports.presentation.fragment.details.adapter.DetailsPagerAdapter
import com.app.sports.utils.MyUtils.loadImg
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeagueDetailsFragment : BaseFragment<FragmentLeagueDetailsBinding, MainViewModel>() {

    private val viewModelShared: MainViewModel by activityViewModels()
    private val args: LeagueDetailsFragmentArgs by navArgs()
    private val leagueId get() = args.leagueId

    private val detailsPagerAdapter by lazy { DetailsPagerAdapter(parentFragmentManager, lifecycle) }

    override fun layoutResource(): Int = R.layout.fragment_league_details

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setUI(savedInstanceState: Bundle?) {
        setupTabLayout()
    }

    override fun observer() {
        lifecycleScope.launchWhenStarted {
            viewModelShared.leagueDetailsState.collect {
                dataBinding.data = it
                dataBinding.ivImg.loadImg(it?.emblem)
            }
        }
    }

    override fun clicks() {
    }

    override fun callApis() {
        viewModelShared.getLeagueDetailsWithTeams(leagueId)
    }

    private fun setupTabLayout() {
        dataBinding.tabLayout.apply {
            addTab(newTab().setText(getString(R.string.seasons)))
            addTab(newTab().setText(getString(R.string.teams)))

            addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let { dataBinding.viewPager.currentItem = it.position }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }

        dataBinding.viewPager.apply {
            adapter = detailsPagerAdapter

            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    dataBinding.tabLayout.selectTab(dataBinding.tabLayout.getTabAt(position))
                }
            })
        }
    }
}
