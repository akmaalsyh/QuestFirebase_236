package com.example.questfirebase_236.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questfirebase_236.modeldata.DetailSiswa
import com.example.questfirebase_236.modeldata.UIStateSiswa
import com.example.questfirebase_236.modeldata.toDataSiswa
import com.example.questfirebase_236.modeldata.toUiStateSiswa
import com.example.questfirebase_236.repositori.RepositorySiswa
import com.example.questfirebase_236.view.route.DestinasiDetail
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {
    var editUiState by mutableStateOf(UIStateSiswa())
        private set

    private val itemId: String = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    init {
        viewModelScope.launch {
            editUiState = repositorySiswa.getSiswaById(itemId).toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        editUiState = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validateInput(detailSiswa)
        )
    }

    private fun validateInput(uiState: DetailSiswa = editUiState.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    suspend fun updateSiswa() {
        if (validateInput()) {
            repositorySiswa.updateSiswa(editUiState.detailSiswa.toDataSiswa())
        }
    }
}