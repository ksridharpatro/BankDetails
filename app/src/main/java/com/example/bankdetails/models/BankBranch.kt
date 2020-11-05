package com.example.bankdetails.models

data class BankBranch(
    val address: String,
    val bank: String,
    val bankCode: String,
    val branch: String,
    val center: String,
    val city: String,
    val contact: String,
    val district: String,
    val ifsc: String,
    val imps: Boolean,
    val micr: String,
    val neft: Boolean,
    val rtgs: Boolean,
    val state: String,
    val upi: Boolean
)