package com.example.bankdetails.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "bank_branch"
)
data class BankBranch(
    @PrimaryKey
    @SerializedName("IFSC")
    val ifsc: String
)