package com.suresh.nocontacts.ui.newcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.suresh.nocontacts.constants.AppConstants
import com.suresh.nocontacts.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewContactBinding
    private lateinit var name: String
    private lateinit var number: String
    private lateinit var message: String
    private lateinit var newContactViewModel: NewContactViewModel
    private var isEditContact = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newContactViewModel = ViewModelProvider(this)[NewContactViewModel::class.java]

        val extras: Bundle? = intent.extras
        isEditContact = extras!!.getBoolean(AppConstants.KEY_EDIT_CONTACT, false)
        number = extras.getString(AppConstants.KEY_SELECTED_NUMBER).toString()

        binding.buttonSave.setOnClickListener {
            saveContact(isEditContact)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isEditContact) {
            val contact = newContactViewModel.getContact(this, number)
            if (contact != null) {
                binding.editTextName.setText(contact.name)
                binding.editTextPhoneNumber.setText(contact.number)
                binding.editTextMessage.setText(contact.message)
            }
        }
    }

    private fun saveContact(isEditContact: Boolean) {
        name = binding.editTextName.text.toString()
        number = binding.editTextPhoneNumber.text.toString()
        message = binding.editTextMessage.text.toString()
        newContactViewModel.saveContact(
            this@NewContactActivity,
            name,
            number,
            message,
            false,
            isEditContact
        )
    }
}