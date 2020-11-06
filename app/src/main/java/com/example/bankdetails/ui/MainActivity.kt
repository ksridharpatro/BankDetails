package com.example.bankdetails.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.bankdetails.R
import com.example.bankdetails.models.User
import com.example.bankdetails.util.SharedPreferenceUtil

class MainActivity : AppCompatActivity(), UserDetailsFragment.InteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = SharedPreferenceUtil(application).getUser()
        if (user == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, UserDetailsFragment.newInstance())
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, BankDetailsFragment.newInstance(user))
            }
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is UserDetailsFragment) {
            fragment.setOnHeadlineSelectedListener(this)
        }
    }

    override fun onUserDetailsSubmitClick(user: User) {
        SharedPreferenceUtil(application).saveUser(user)
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, BankDetailsFragment.newInstance(user))
        }
    }
}