package com.suresh.nocontacts.ui.newcontact

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.suresh.nocontacts.preferences.AppPreferences

class NewContactViewModel : ViewModel() {
    fun saveContact(activity: NewContactActivity, name: String, number: String, message: String) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number) || TextUtils.isEmpty(message)) {
            Toast.makeText(activity, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (isContactAlreadySaved(activity, number)) {
            Toast.makeText(activity, "Contact already there", Toast.LENGTH_SHORT).show()
            return
        }

        AppPreferences(activity).saveContact(name, number, message)
        activity.finish()
    }

    private fun isContactAlreadySaved(activity: NewContactActivity, number: String): Boolean {
        return AppPreferences(activity).getContact(number) != null
    }
}