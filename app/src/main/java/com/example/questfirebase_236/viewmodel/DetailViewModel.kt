package com.example.questfirebase_236.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questfirebase_236.modeldata.Siswa
import com.example.questfirebase_236.repositori.RepositorySiswa
import com.example.questfirebase_236.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {
    private val itemId: String = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var detailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getSiswaById()
    }

    private fun getSiswaById() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = repositorySiswa.getSiswaById(itemId)
                detailUiState = DetailUiState(detailSiswa = result)
            } catch (e: Exception) {
                detailUiState = DetailUiState(isError = true)
            }
        }
    }

    data class DetailUiState(
        val detailSiswa: Siswa = Siswa(),
        val isLoading: Boolean = false,
        val isError: Boolean = false
    )
}