package com.example.questfirebase_236.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.questfirebase_236.modeldata.DetailSiswa
import com.example.questfirebase_236.modeldata.UIStateSiswa
import com.example.questfirebase_236.modeldata.toDataSiswa
import com.example.questfirebase_236.repositori.RepositorySiswa

class EntryViewModel(
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    // Mengobservasi state untuk UI
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Memperbarui state berdasarkan input pengguna
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validateInput(detailSiswa)
        )
    }

    // Fungsi untuk memvalidasi apakah input sudah terisi semua
    private fun validateInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // Menyimpan data ke Firestore melalui repository
    suspend fun addSiswa() {
        if (validateInput()) {
            repositorySiswa.insertSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}