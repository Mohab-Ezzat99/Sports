package com.app.sports.presentation.fragment.details

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.sports.R
import com.app.sports.base.BaseFragment
import com.app.sports.databinding.FragmentTeamsBinding
import com.app.sports.presentation.MainViewModel
import com.app.sports.presentation.fragment.details.adapter.TeamAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : BaseFragment<FragmentTeamsBinding, MainViewModel>() {

    private val viewModelShared: MainViewModel by activityViewModels()
    private val teamAdapter by lazy { TeamAdapter() }

    override fun layoutResource(): Int = R.layout.fragment_teams

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun setUI(savedInstanceState: Bundle?) {
        dataBinding.rvTeams.adapter = teamAdapter
    }

    override fun observer() {
        lifecycleScope.launchWhenStarted {
            viewModelShared.leagueTeamsState.collect {
                teamAdapter.submitList(it)
            }
        }
    }

    override fun clicks() {
        teamAdapter.onFavClicked { item, position ->
            item.isFavourite = !item.isFavourite
            viewModelShared.updateTeam(item)
            teamAdapter.notifyItemChanged(position)
        }
    }

    override fun callApis() {
    }
}
