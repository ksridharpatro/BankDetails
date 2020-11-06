package com.example.bankdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                getUserFromUI()?.let {
                    onUserDetailsSubmitClick(it)
                    return@run
                }
                Toast.makeText(requireContext(), "Enter details", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getUserFromUI(): User? {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        if (firstName.isBlank() || lastName.isBlank()) {
            return null
        } else {
            return User(firstName, lastName)
        }
    }

    fun setInteractionListener(callback: InteractionListener) {
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