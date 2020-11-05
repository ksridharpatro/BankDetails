package com.example.bankdetails.api

import com.example.bankdetails.models.BankBranch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApi {

    @GET("{ifsc}")
    fun getBankBranch(@Path("ifsc") ifsc: String): Call<BankBranch>
}