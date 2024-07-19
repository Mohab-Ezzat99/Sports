package com.app.sports.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LeagueDetailsDto(
    @SerializedName("area") var area: Area? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("emblem") var emblem: String? = null,
    @SerializedName("currentSeason") var currentSeason: CurrentSeason? = null,
    @SerializedName("seasons") var seasons: ArrayList<Seasons>? = null,
    @SerializedName("lastUpdated") var lastUpdated: String? = null
)

data class Seasons(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("startDate") var startDate: String? = null,
    @SerializedName("endDate") var endDate: String? = null,
    @SerializedName("currentMatchday") var currentMatchday: Int? = null,
    @SerializedName("winner") var winner: Any? = null
)
