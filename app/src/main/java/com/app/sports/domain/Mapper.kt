package com.app.sports.domain

import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.data.remote.dto.Team

fun Team.toTeamEntity(): TeamEntity {
    return TeamEntity(id, name, shortName, tla, crest, address, website, founded, clubColors, venue, lastUpdated)
}
