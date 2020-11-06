package com.example.bankdetails.ui

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.bankdetails.R
import com.example.bankdetails.models.User
import kotlinx.android.synthetic.main.fragment_user_details.*


class UserDetailsFragment : Fragment() {

    private val permissionRequestCodeReadExtStorage: Int = 1
    private var callback: InteractionListener? = null
    private val pickImage = 1
    private var imagePath: String? = null

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
            if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PermissionChecker.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    Array(1) { Manifest.permission.READ_EXTERNAL_STORAGE },
                    permissionRequestCodeReadExtStorage
                );
            } else {
                openGallery()
            }
        }
    }

    fun openGallery() {
        val intent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, pickImage)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionRequestCodeReadExtStorage) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            val selectedImageUri: Uri? = data?.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            selectedImageUri?.let {
                val cursor: Cursor? = context?.contentResolver?.query(
                    it, filePathColumn, null, null, null
                )
                cursor?.run {
                    moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    imagePath = cursor.getString(columnIndex)
                    close()
                    val bitmap = BitmapFactory.decodeFile(imagePath)
                    ivProfilePhoto.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun getUserFromUI(): User? {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        return if (firstName.isBlank() || lastName.isBlank()) {
            null
        } else {
            User(firstName, lastName, imagePath)
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