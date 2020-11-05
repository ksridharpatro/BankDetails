package com.example.bankdetails.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_branch"
)
data class BankBranch(
    @PrimaryKey
    val ifsc: String,
    val address: String,
    val bank: String,
    val bankCode: String,
    val branch: String,
    val center: String,
    val city: String,
    val contact: String,
    val district: String,
    val imps: Boolean,
    val micr: String,
    val neft: Boolean,
    val rtgs: Boolean,
    val state: String,
    val upi: Boolean
)