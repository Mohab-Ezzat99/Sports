package com.app.sports.presentation

import androidx.lifecycle.viewModelScope
import com.app.sports.base.BaseViewModel
import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.data.remote.dto.LeagueDetailsDto
import com.app.sports.domain.usecase.GetLeagueDetailsUseCase
import com.app.sports.domain.usecase.GetLeagueTeamsUseCase
import com.app.sports.domain.usecase.UpdateTeamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLeagueDetailsUseCase: GetLeagueDetailsUseCase,
    private val getLeagueTeamsUseCase: GetLeagueTeamsUseCase,
    private val updateTeamUseCase: UpdateTeamUseCase
) : BaseViewModel() {

    private val _leagueDetailsState by lazy { MutableStateFlow<LeagueDetailsDto?>(null) }
    val leagueDetailsState: StateFlow<LeagueDetailsDto?> by lazy { _leagueDetailsState }

    private val _leagueTeamsState by lazy { MutableStateFlow<List<TeamEntity>?>(null) }
    val leagueTeamsState: StateFlow<List<TeamEntity>?> by lazy { _leagueTeamsState }

    fun getLeagueDetailsWithTeams(id: Int) = viewModelScope.launch {
        loading.value = true
        listOf(getLeagueDetails(id), getLeagueTeams(id)).joinAll()
        loading.value = false
    }

    private fun getLeagueDetails(id: Int) = viewModelScope.launch {
        getLeagueDetailsUseCase(id).collect {
            _leagueDetailsState.value = it
        }
    }

    private fun getLeagueTeams(id: Int) = viewModelScope.launch {
        getLeagueTeamsUseCase(id).collect {
            _leagueTeamsState.value = it
        }
    }

    fun updateTeam(teamEntity: TeamEntity) = viewModelScope.launch {
        updateTeamUseCase(teamEntity)
    }
}
