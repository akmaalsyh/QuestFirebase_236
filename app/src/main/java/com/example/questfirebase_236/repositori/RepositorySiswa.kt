package com.example.questfirebase_236.repositori

import com.example.questfirebase_236.modeldata.Siswa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface RepositorySiswa {
    suspend fun getAllSiswa(): List<Siswa>
    suspend fun insertSiswa(siswa: Siswa)
    suspend fun updateSiswa(siswa: Siswa)
    suspend fun deleteSiswa(siswa: Siswa)
    suspend fun getSiswaById(id: String): Siswa
}

class FirebaseRepositorySiswa : RepositorySiswa {
    private val firestore = FirebaseFirestore.getInstance()
    private val siswaCollection = firestore.collection("Siswa")

    override suspend fun getAllSiswa(): List<Siswa> {
        return try {
            val snapshot = siswaCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject(Siswa::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun insertSiswa(siswa: Siswa) {
        try {
            siswaCollection.add(siswa).await()
        } catch (e: Exception) {
            throw e
        }
    }

    // Di dalam class FirebaseRepositorySiswa
    override suspend fun updateSiswa(siswa: Siswa) {
        try {
            siswaCollection.document(siswa.id).set(siswa).await() // Ganti nim ke id
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun deleteSiswa(siswa: Siswa) {
        try {
            siswaCollection.document(siswa.id).delete().await() // Ganti nim ke id
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getSiswaById(id: String): Siswa {
        return try {
            val document = siswaCollection.document(id).get().await()
            document.toObject(Siswa::class.java) ?: Siswa()
        } catch (e: Exception) {
            Siswa()
        }
    }
}