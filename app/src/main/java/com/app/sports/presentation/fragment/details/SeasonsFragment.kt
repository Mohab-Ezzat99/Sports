package com.app.sports.presentation.fragment.details

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.sports.R
import com.app.sports.base.BaseFragment
import com.app.sports.databinding.FragmentSeasonsBinding
import com.app.sports.presentation.MainViewModel
import com.app.sports.presentation.fragment.details.adapter.SeasonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeasonsFragment : BaseFragment<FragmentSeasonsBinding, MainViewModel>() {

    private val viewModelShared: MainViewModel by activityViewModels()
    private val seasonAdapter by lazy { SeasonAdapter() }

    override fun layoutResource(): Int = R.layout.fragment_seasons

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setUI(savedInstanceState: Bundle?) {
        dataBinding.rvSeasons.adapter = seasonAdapter
    }

    override fun observer() {
        lifecycleScope.launchWhenStarted {
            viewModelShared.leagueDetailsState.collect {
                seasonAdapter.submitList(it?.seasons)
            }
        }
    }

    override fun clicks() {
    }

    override fun callApis() {
    }
}
