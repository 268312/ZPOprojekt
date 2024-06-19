package com.example.projektzpo

interface FireStoreInterface {

    suspend fun addData(email: String, data: FireStoreData)

    suspend fun addMeasurement(email: String, date: String, data: Measurement): Result<Unit>

    suspend fun getMeasurement(email: String): List<Pair<String, Measurement>>

    suspend fun updateData(email: String, updatedData: FireStoreData)

    suspend fun getData(email: String): FireStoreData?

    suspend fun deleteData(email: String)
}