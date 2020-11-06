package com.example.bankdetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.repository.BankRepository

class BankViewModelProviderFactory(
    private val bankRepository: BankRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BankViewModel(bankRepository) as T
    }
}