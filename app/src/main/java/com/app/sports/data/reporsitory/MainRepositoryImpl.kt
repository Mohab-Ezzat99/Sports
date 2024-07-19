package com.app.sports.data.reporsitory

import com.app.sports.data.local.AppDao
import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.data.remote.RetrofitApi
import com.app.sports.data.remote.networkHandling.NetworkResult.getResult
import com.app.sports.domain.repository.MainRepository
import com.app.sports.domain.toTeamEntity
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: RetrofitApi,
    private val dao: AppDao,
) : MainRepository {

    override suspend fun getAllLeagues() = getResult { apiService.getAllLeagues() }

    override suspend fun getLeagueDetailsById(id: Int) = getResult { apiService.getLeagueDetailsById(id) }

    override suspend fun getLeagueTeamsById(id: Int) = getResult { apiService.getLeagueTeamsById(id) }.let {
        val teams = it.data?.teams?.map { it.toTeamEntity() }
        if (teams.isNullOrEmpty()) null
        else {
            dao.upsertTeams(teams)
            dao.getTeamsList()
        }
    }

    override suspend fun updateTeam(teamEntity: TeamEntity) {
        dao.upsertTeam(teamEntity)
    }

    override suspend fun getFavouriteTeams(): List<TeamEntity>? {
        return dao.getFavouriteTeams()
    }
}
