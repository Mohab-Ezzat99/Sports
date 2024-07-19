package com.app.sports.presentation.fragment.favourites

import androidx.lifecycle.viewModelScope
import com.app.sports.base.BaseViewModel
import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.domain.usecase.GetFavouriteTeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class FavouritesLeaguesViewModel @Inject constructor(
    private val getFavouriteTeamsUseCase: GetFavouriteTeamsUseCase
) : BaseViewModel() {

    private val _favTeamsState by lazy { MutableStateFlow<List<TeamEntity>?>(null) }
    val favTeamsState: StateFlow<List<TeamEntity>?> by lazy { _favTeamsState }

    fun getFavouriteTeams() {
        viewModelScope.launch {
            loading.value = true
            getFavouriteTeamsUseCase().collect {
                loading.value = false
                _favTeamsState.value = it
            }
        }
    }
}
