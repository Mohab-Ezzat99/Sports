package com.app.sports.domain.usecase

import com.app.sports.data.remote.dto.Competitions
import com.app.sports.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class GetAllLeaguesUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke(): Flow<List<Competitions>?> = flow {
        emit(mainRepository.getAllLeagues().data?.competitions)
    }
}
