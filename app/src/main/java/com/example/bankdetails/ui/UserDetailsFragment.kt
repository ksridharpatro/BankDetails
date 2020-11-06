package com.example.bankdetails.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
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
    private val pickImage = 1
    private var imageUriString: String? = null

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
        ivProfilePhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUriString = data?.data.toString()
            ivProfilePhoto.setImageURI(data?.data);
        }
    }

    private fun getUserFromUI(): User? {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        if (firstName.isBlank() || lastName.isBlank()) {
            return null
        } else {
            return User(firstName, lastName, imageUriString)
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