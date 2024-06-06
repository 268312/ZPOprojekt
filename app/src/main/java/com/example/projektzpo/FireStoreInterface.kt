package com.example.projektzpo

interface FireStoreInterface {

    suspend fun addData(email: String, data: FireStoreData)

    suspend fun updateData(email: String, updatedData: FireStoreData)

    suspend fun getData(email: String): FireStoreData?

    suspend fun deleteData(email: String)
}