package com.example.projektzpo

import com.google.firebase.Timestamp

data class Measurement(
    var email: String,
    var time: Timestamp,
    var weight: Double?,
    var bloodPressure: String,
    var pulse: Int?,
    var saturation: Int?,
    var glucose: Double?,
    var cholesterole: Double?)