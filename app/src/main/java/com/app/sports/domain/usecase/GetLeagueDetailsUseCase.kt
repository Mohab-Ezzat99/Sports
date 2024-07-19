package com.app.sports.domain.usecase

import com.app.sports.data.remote.dto.LeagueDetailsDto
import com.app.sports.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class GetLeagueDetailsUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(id: Int): Flow<LeagueDetailsDto?> = flow {
        emit(mainRepository.getLeagueDetailsById(id).data)
    }
}
