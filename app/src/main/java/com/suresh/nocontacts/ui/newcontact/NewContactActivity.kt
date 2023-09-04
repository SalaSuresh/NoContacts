package com.suresh.nocontacts.ui.newcontact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.suresh.nocontacts.databinding.ActivityNewContactBinding

class NewContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewContactBinding
    private lateinit var name: String
    private lateinit var number: String
    private lateinit var message: String
    private lateinit var newContactViewModel:NewContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newContactViewModel = ViewModelProvider(this)[NewContactViewModel::class.java]

        binding.buttonSave.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        name = binding.editTextName.text.toString()
        number = binding.editTextPhoneNumber.text.toString()
        message = binding.editTextMessage.text.toString()
        newContactViewModel.saveContact(name, number, message)
    }
}