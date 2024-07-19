package com.app.sports.presentation.fragment.allLeagues

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.sports.R
import com.app.sports.base.BaseFragment
import com.app.sports.databinding.FragmentAllLeaguesBinding
import com.app.sports.presentation.fragment.details.LeagueDetailsFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AllLeaguesFragment : BaseFragment<FragmentAllLeaguesBinding, AllLeaguesViewModel>() {

    private val allLeaguesAdapter by lazy { AllLeaguesAdapter() }

    override fun layoutResource(): Int = R.layout.fragment_all_leagues

    override fun viewModelClass(): Class<AllLeaguesViewModel> = AllLeaguesViewModel::class.java

    override fun setUI(savedInstanceState: Bundle?) {
        setupSearch()
        dataBinding.rvCompetitions.adapter = allLeaguesAdapter
    }

    override fun observer() {
        lifecycleScope.launchWhenStarted {
            viewModel.allLeaguesState.collect {
                if (it.isNullOrEmpty().not()) {
                    allLeaguesAdapter.setData(it)
                    dataBinding.etSearch.isVisible = true
                }
            }
        }
    }

    override fun clicks() {
        dataBinding.fabFavourites.setOnClickListener {
            findNavController().navigate(R.id.favouriteLeaguesFragment)
        }

        allLeaguesAdapter.onItemClicked {
            it.id?.let { id ->
                findNavController().navigate(
                    R.id.leagueDetailsFragment,
                    LeagueDetailsFragmentArgs(id).toBundle()
                )
            }
        }
    }

    override fun callApis() {
        viewModel.getAllLeagues()
    }

    private fun setupSearch() {
        dataBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                allLeaguesAdapter.filter.filter(s)
            }
        })
    }
}
