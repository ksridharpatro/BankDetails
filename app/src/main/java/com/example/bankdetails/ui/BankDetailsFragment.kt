package com.example.bankdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bankdetails.BankDetailsApplication
import com.example.bankdetails.R
import com.example.bankdetails.db.BankDatabase
import com.example.bankdetails.repository.BankRepository
import com.example.bankdetails.util.Status
import kotlinx.android.synthetic.main.fragment_bank_details.*

class BankDetailsFragment : Fragment() {

    private lateinit var viewModel: BankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bank_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val executor =
            (requireActivity().application as BankDetailsApplication).getThreadPoolExecutor()
        val bankRepository = BankRepository(BankDatabase(requireContext()), executor)
        val viewModelProviderFactory = BankViewModelProviderFactory(bankRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(BankViewModel::class.java)
        btnSearch.setOnClickListener {
            onSearchClick(etIfsc.text.toString())
        }
    }

    private fun onSearchClick(ifsc: String) {
        val bankBranchResource = viewModel.getBankBranchResource(ifsc)
        bankBranchResource.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    tvBankDetails.text = it.data.toString()
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    tvBankDetails.text = it.message
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = BankDetailsFragment()
    }
}