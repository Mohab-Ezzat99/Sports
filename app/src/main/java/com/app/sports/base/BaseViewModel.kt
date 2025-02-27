package com.app.sports.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {
    val loading = MutableLiveData<Boolean>()
    val showMassage = MutableLiveData<String>()
}
