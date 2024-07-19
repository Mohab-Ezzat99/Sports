package com.app.sports.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    var id: Int? = null,
    var name: String? = null,
    var shortName: String? = null,
    var tla: String? = null,
    var crest: String? = null,
    var address: String? = null,
    var website: String? = null,
    var founded: Int? = null,
    var clubColors: String? = null,
    var venue: String? = null,
    var lastUpdated: String? = null,
    @PrimaryKey(autoGenerate = true)
    val localId: Int? = null,
    var isFavourite: Boolean = false,
)
