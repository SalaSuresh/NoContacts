package com.suresh.nocontacts.ui.nocontacts

import android.content.Context
import androidx.lifecycle.ViewModel
import com.suresh.nocontacts.model.Contact
import com.suresh.nocontacts.preferences.AppPreferences

class NoContactsViewModel : ViewModel() {

    fun getAllContacts(requireContext: Context): ArrayList<Contact> {
        return AppPreferences(requireContext).getAllContacts()
    }
}