package com.example.bankdetails.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.bankdetails.models.BankBranch

@Dao
interface BankDao {

    @Query("SELECT * FROM bank_branch WHERE ifsc = :ifsc")
    fun getBankBranch(ifsc: String): BankBranch?

    @Insert(onConflict = REPLACE)
    fun saveBankBranch(bankBranch: BankBranch)
}