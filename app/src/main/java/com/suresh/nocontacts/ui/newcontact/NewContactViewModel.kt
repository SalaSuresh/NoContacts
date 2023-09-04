package com.suresh.nocontacts.ui.newcontact

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel

class NewContactViewModel: ViewModel() {
    fun saveContact(name: String, number: String, message: String) {
        Log.d("test", "saveContact() called with: name = $name, number = $number, message = $message")
    }
}