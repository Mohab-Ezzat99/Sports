package com.app.sports.domain.usecase

import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTeamUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(teamEntity: TeamEntity) = mainRepository.updateTeam(teamEntity)
}
