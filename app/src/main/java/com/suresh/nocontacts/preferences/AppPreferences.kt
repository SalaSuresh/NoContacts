package com.suresh.nocontacts.preferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.suresh.nocontacts.model.Contact


class AppPreferences(context: Context) {
    private var sharedPreference: SharedPreferences? = null
    private var editor: Editor? = null

    init {
        if (sharedPreference == null) {
            sharedPreference = context.getSharedPreferences(
                PreferenceConstants.PREF_NAME,
                AppCompatActivity.MODE_PRIVATE
            )
        }
    }

    private fun getPreferenceEditor(): Editor {
        if (editor == null) {
            editor = sharedPreference!!.edit()
        }
        return editor!!
    }

    fun saveContact(name: String, number: String, message: String) {
        val contact = Contact(name, number, message)
        val contactJson: String = Gson().toJson(contact)
        getPreferenceEditor().putString(number, contactJson)
        getPreferenceEditor().apply()
    }

    fun getContact(number: String): Contact? {
        val contactJson = sharedPreference!!.getString(number, "")

        return if (TextUtils.isEmpty(contactJson)) {
            null
        } else {
            Gson().fromJson(contactJson, Contact::class.java)
        }
    }

    fun getAllContacts(): ArrayList<Contact> {
        val allContacts = ArrayList<Contact>()
        val allPrefs: Map<String, *> = sharedPreference!!.all
        for (key in allPrefs.keys) {
            val value: String? = sharedPreference!!.getString(key, "")
            val contact = Gson().fromJson(value, Contact::class.java)
            allContacts.add(contact)
        }
        return allContacts
    }
}