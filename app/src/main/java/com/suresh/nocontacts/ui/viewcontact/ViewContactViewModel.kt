package com.suresh.nocontacts.ui.viewcontact

import android.content.Context
import androidx.lifecycle.ViewModel
import com.suresh.nocontacts.model.Contact
import com.suresh.nocontacts.preferences.AppPreferences

class ViewContactViewModel: ViewModel() {
    fun getContact(context: Context, number: String): Contact? {
        return AppPreferences(context).getContact(number)
    }

}