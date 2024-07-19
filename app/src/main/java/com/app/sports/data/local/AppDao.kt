package com.app.sports.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.sports.data.local.entity.TeamEntity

@Dao
interface AppDao {

    @Upsert
    suspend fun upsertTeam(team: TeamEntity)

    @Upsert
    suspend fun upsertTeams(teams: List<TeamEntity>)

    @Query("SELECT * FROM TeamEntity")
    suspend fun getTeamsList(): List<TeamEntity>

    @Query("SELECT * FROM TeamEntity WHERE isFavourite = 1")
    suspend fun getFavouriteTeams(): List<TeamEntity>?
}
