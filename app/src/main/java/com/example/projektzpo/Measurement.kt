package com.example.projektzpo

import com.google.firebase.firestore.PropertyName

data class Measurement(
    @get:PropertyName("weight") @set:PropertyName("weight") var weight: Double?,
    @get:PropertyName("bloodPressure") @set:PropertyName("bloodPressure") var bloodPressure: String?,
    @get:PropertyName("pulse") @set:PropertyName("pulse") var pulse: Int?,
    @get:PropertyName("saturation") @set:PropertyName("saturation") var saturation: Int?,
    @get:PropertyName("glucose") @set:PropertyName("glucose") var glucose: Double?,
    @get:PropertyName("cholesterole") @set:PropertyName("cholesterole") var cholesterole: Double?)