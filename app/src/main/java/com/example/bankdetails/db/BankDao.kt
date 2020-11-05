package com.example.bankdetails.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.bankdetails.models.BankBranch

@Dao
interface BankDao {

    suspend fun getBankBranchByIfsc(ifsc: String): BankBranch

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBankBranch(bankBranch: BankBranch)
}