package com.suresh.nocontacts.ui.viewcontact

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.suresh.nocontacts.constants.AppConstants
import com.suresh.nocontacts.databinding.ActivityViewContactBinding
import com.suresh.nocontacts.model.Contact
import com.suresh.nocontacts.ui.newcontact.NewContactActivity

class ViewContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewContactBinding
    private lateinit var number: String
    private lateinit var viewContactViewModel: ViewContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewContactViewModel = ViewModelProvider(this)[ViewContactViewModel::class.java]
        val extras: Bundle? = intent.extras
        number = extras?.getString(AppConstants.KEY_SELECTED_NUMBER).toString()
        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, NewContactActivity::class.java)
            intent.putExtra(AppConstants.KEY_EDIT_CONTACT, true)
            intent.putExtra(AppConstants.KEY_SELECTED_NUMBER, number)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        val contact: Contact? = viewContactViewModel.getContact(this@ViewContactActivity, number)
        if (contact != null) {
            binding.textName.text = contact.name
            binding.textNumber.text = contact.number
            binding.textMessage.text = contact.message
        }
    }
}