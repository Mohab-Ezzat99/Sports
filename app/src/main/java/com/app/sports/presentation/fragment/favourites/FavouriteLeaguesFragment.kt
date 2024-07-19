package com.app.sports.presentation.fragment.favourites

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.app.sports.R
import com.app.sports.base.BaseFragment
import com.app.sports.databinding.FragmentFavouriteLeaguesBinding
import com.app.sports.presentation.MainViewModel
import com.app.sports.presentation.fragment.details.adapter.TeamAdapter
import com.app.sports.utils.MyUtils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteLeaguesFragment : BaseFragment<FragmentFavouriteLeaguesBinding, FavouritesLeaguesViewModel>() {

    private val viewModelShared: MainViewModel by activityViewModels()
    private val teamAdapter by lazy { TeamAdapter() }

    override fun layoutResource(): Int = R.layout.fragment_favourite_leagues

    override fun viewModelClass(): Class<FavouritesLeaguesViewModel> = FavouritesLeaguesViewModel::class.java

    override fun setUI(savedInstanceState: Bundle?) {
        dataBinding.rvTeams.adapter = teamAdapter
    }

    override fun observer() {
        lifecycleScope.launchWhenStarted {
            viewModel.favTeamsState.collect { teams ->
                if (teams?.isEmpty() == true) showToast(getString(R.string.noFav))
                else teamAdapter.submitList(teams)
            }
        }
    }

    override fun clicks() {
        teamAdapter.onFavClicked { item, _ ->
            item.isFavourite = false
            viewModelShared.updateTeam(item)
            val list = teamAdapter.currentList.toMutableList()
            list.remove(item)
            teamAdapter.submitList(list)
        }
    }

    override fun callApis() {
        viewModel.getFavouriteTeams()
    }
}
