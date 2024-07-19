package com.app.sports.domain.repository

import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.data.remote.dto.AllLeaguesDto
import com.app.sports.data.remote.dto.LeagueDetailsDto
import com.app.sports.data.remote.networkHandling.Resource

interface MainRepository {

    suspend fun getAllLeagues(): Resource<AllLeaguesDto>

    suspend fun getLeagueDetailsById(id: Int): Resource<LeagueDetailsDto>

    suspend fun getLeagueTeamsById(id: Int): List<TeamEntity>?

    suspend fun updateTeam(teamEntity: TeamEntity)

    suspend fun getFavouriteTeams(): List<TeamEntity>?
}
