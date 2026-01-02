package com.example.questfirebase_236.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questfirebase_236.modeldata.Siswa
import com.example.questfirebase_236.repositori.RepositorySiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    // Mengelola status UI (Loading, Success, Error)
    // HomeViewModel.kt
    val homeUiState: StateFlow<HomeUiState> = flow {
        emit(repositorySiswa.getAllSiswa())
    }
        .map<List<Siswa>, HomeUiState> { HomeUiState.Success(it) } // Tambahkan tipe eksplisit
        .catch { emit(HomeUiState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

    sealed interface HomeUiState {
        data class Success(val listSiswa: List<Siswa>) : HomeUiState
        object Error : HomeUiState
        object Loading : HomeUiState
    }
}