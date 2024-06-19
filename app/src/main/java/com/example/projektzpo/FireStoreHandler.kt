package com.example.projektzpo

import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class FireStoreHandler(private val db: FirebaseFirestore) : FireStoreInterface {

    override suspend fun addData(email: String, data: FireStoreData) {
        try {
            db.collection("users").document(email).set(data).await()
        } catch (e: Exception) {
            //
        }
    }

    override suspend fun addMeasurement(email: String, date: String, data: Measurement): Result<Unit> {
        return try {
            db.collection("measurements").document(email).collection("measurement").document(date).set(data).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getData(email: String): FireStoreData? {
        val snapshot = FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo(FieldPath.documentId(), email)
            .get()
            .await()
        return snapshot.documents.firstOrNull()?.toObject(FireStoreData::class.java)
    }

    override suspend fun updateData(email: String, updatedData: FireStoreData) {
        try {
            db.collection("users").document(email).set(updatedData).await()
        } catch (e: Exception) {
            //
        }
    }

    override suspend fun deleteData(email: String) {
        try {
            db.collection("users").document(email).delete().await()
        } catch (e: Exception) {
            //
        }
    }
}