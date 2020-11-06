package com.example.bankdetails.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bankdetails.api.RetrofitInstance
import com.example.bankdetails.db.BankDatabase
import com.example.bankdetails.models.BankBranch
import com.example.bankdetails.util.Resource
import java.io.IOException
import java.util.concurrent.ThreadPoolExecutor

class BankRepository(
    private val db: BankDatabase,
    private val executor: ThreadPoolExecutor,
) {

    private val _bankBranchResource: MutableLiveData<Resource<BankBranch>> = MutableLiveData()

    fun getBankBranchResource(ifsc: String): LiveData<Resource<BankBranch>> {
        _bankBranchResource.postValue(Resource.loading())
        executor.execute {
            fetchBankBranch(ifsc)
        }
        return _bankBranchResource
    }

    private fun fetchBankBranch(ifsc: String) {
        try {
            var bankBranch = db.getBankDao().getBankBranch(ifsc)
            bankBranch?.let {
                _bankBranchResource.postValue(Resource.success(it))
                return
            }
            bankBranch = RetrofitInstance.bankApi.getBankBranch(ifsc).execute().body()
            bankBranch?.let {
                db.getBankDao().saveBankBranch(it)
                _bankBranchResource.postValue(Resource.success(it))
                return
            }
            _bankBranchResource.postValue(Resource.error("Not found"))
        } catch (e: IOException) {
            _bankBranchResource.postValue(Resource.error("Error while fetching"))
        }
    }
}