package com.app.sports.domain.usecase

import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class GetLeagueTeamsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(id: Int): Flow<List<TeamEntity>?> = flow {
        emit(mainRepository.getLeagueTeamsById(id))
    }
}
