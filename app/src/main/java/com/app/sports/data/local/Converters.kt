package com.app.sports.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.app.sports.data.local.entity.TeamEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun toTeamsListJson(list: List<TeamEntity>): String {
        return gson.toJson(
            list,
            object : TypeToken<ArrayList<TeamEntity>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromTeamsListJson(json: String): List<TeamEntity> {
        return gson.fromJson<ArrayList<TeamEntity>>(
            json,
            object : TypeToken<ArrayList<TeamEntity>>() {}.type
        ) ?: emptyList()
    }
}
