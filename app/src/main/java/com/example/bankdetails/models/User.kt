package com.example.bankdetails.models

import java.io.Serializable

data class User(
    val firstName: String,
    val lastName: String,
    val imagePath: String?,
) : Serializable