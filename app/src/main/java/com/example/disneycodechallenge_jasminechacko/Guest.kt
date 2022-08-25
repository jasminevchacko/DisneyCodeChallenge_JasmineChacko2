package com.example.disneycodechallenge_jasminechacko

data class Guest (
    val title: String,
    var isChecked: Boolean = false,
    var hasReservation: Boolean
)