package com.app.sports.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LeagueTeamsDto(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("filters") var filters: Filters? = null,
    @SerializedName("competition") var competition: Competitions? = null,
    @SerializedName("season") var season: Seasons? = null,
    @SerializedName("teams") var teams: ArrayList<Team>? = null
)

data class Team(
    @SerializedName("area") var area: Area? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("shortName") var shortName: String? = null,
    @SerializedName("tla") var tla: String? = null,
    @SerializedName("crest") var crest: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("website") var website: String? = null,
    @SerializedName("founded") var founded: Int? = null,
    @SerializedName("clubColors") var clubColors: String? = null,
    @SerializedName("venue") var venue: String? = null,
    @SerializedName("runningCompetitions") var runningCompetitions: ArrayList<Any>? = null,
    @SerializedName("coach") var coach: Coach? = null,
    @SerializedName("squad") var squad: ArrayList<Squad>? = null,
    @SerializedName("staff") var staff: ArrayList<Any>? = null,
    @SerializedName("lastUpdated") var lastUpdated: String? = null
)

data class Squad(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("position") var position: String? = null,
    @SerializedName("dateOfBirth") var dateOfBirth: String? = null,
    @SerializedName("nationality") var nationality: String? = null
)

data class Coach(
    @SerializedName("id") var id: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("dateOfBirth") var dateOfBirth: String? = null,
    @SerializedName("nationality") var nationality: String? = null,
    @SerializedName("contract") var contract: Contract? = null
)

data class Contract(
    @SerializedName("start") var start: String? = null,
    @SerializedName("until") var until: String? = null
)
