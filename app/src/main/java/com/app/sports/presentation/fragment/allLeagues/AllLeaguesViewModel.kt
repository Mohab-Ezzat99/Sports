package com.app.sports.presentation.fragment.allLeagues

import androidx.lifecycle.viewModelScope
import com.app.sports.base.BaseViewModel
import com.app.sports.data.remote.dto.Competitions
import com.app.sports.domain.usecase.GetAllLeaguesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class AllLeaguesViewModel @Inject constructor(
    private val getAllLeaguesUseCase: GetAllLeaguesUseCase
) : BaseViewModel() {

    private val _allLeaguesState by lazy { MutableStateFlow<List<Competitions>?>(null) }
    val allLeaguesState: StateFlow<List<Competitions>?> by lazy { _allLeaguesState }

    fun getAllLeagues() {
        viewModelScope.launch {
            loading.value = true
            getAllLeaguesUseCase().collect {
                loading.value = false
                _allLeaguesState.value = it
            }
        }
    }
}
