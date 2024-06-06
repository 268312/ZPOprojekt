package com.example.projektzpo

import com.google.firebase.firestore.PropertyName

data class FireStoreData(
    @get:PropertyName("name") @set:PropertyName("name") var name: String = "",
    @get:PropertyName("sex") @set:PropertyName("sex") var sex: String = "",
    @get:PropertyName("height") @set:PropertyName("height") var height: Int = 0,
    @get:PropertyName("birthYear") @set:PropertyName("birthYear") var birthYear: Int = 0,
)
