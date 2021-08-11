package com.example.chronos.uis.home

import androidx.lifecycle.ViewModel
import com.example.chronos.network.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository): ViewModel() {

}