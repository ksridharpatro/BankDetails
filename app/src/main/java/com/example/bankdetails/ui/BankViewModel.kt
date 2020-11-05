package com.example.bankdetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bankdetails.models.BankBranch
import com.example.bankdetails.repository.BankRepository
import com.example.bankdetails.util.Resource

class BankViewModel(
    private val bankRepository: BankRepository
) : ViewModel() {

    fun getBankBranchResource(ifsc: String): LiveData<Resource<BankBranch>> {
        return bankRepository.getBankBranchResource(ifsc)
    }
}