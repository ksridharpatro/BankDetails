package com.example.bankdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bankdetails.R
import com.example.bankdetails.models.User
import kotlinx.android.synthetic.main.fragment_user_details.*

class UserDetailsFragment : Fragment() {

    private var callback: InteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmit.setOnClickListener {
            callback?.run {
                onUserDetailsSubmitClick(
                    User(
                        etFirstName.text.toString(),
                        etLastName.text.toString()
                    )
                )
            }
        }
    }

    fun setOnHeadlineSelectedListener(callback: InteractionListener) {
        this.callback = callback
    }

    interface InteractionListener {
        fun onUserDetailsSubmitClick(user: User)
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserDetailsFragment()
    }
}