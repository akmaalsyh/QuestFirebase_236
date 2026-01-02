package com.example.questfirebase_236.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questfirebase_236.view.SiswaTopAppBar
import com.example.questfirebase_236.view.route.DestinasiDetail
import com.example.questfirebase_236.viewmodel.DetailViewModel
import com.example.questfirebase_236.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanDetail(
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.detailUiState

    Scaffold(
        topBar = {
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(uiState.detailSiswa.id.toString()) }
            ) {
                Text("Edit")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Nama: ${uiState.detailSiswa.nama}")
                Text("Alamat: ${uiState.detailSiswa.alamat}")
                Text("Telepon: ${uiState.detailSiswa.telpon}")
            }
        }
    }
}