package com.example.bankdetails.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bankdetails.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fragmentContainer, BankDetailsFragment.newInstance()).commit()
    }
}