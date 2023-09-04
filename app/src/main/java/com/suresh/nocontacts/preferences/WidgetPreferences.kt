package com.suresh.nocontacts.preferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity

class WidgetPreferences(context: Context) {
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
        getPreferenceEditor().putString("${number}_${PreferenceConstants.PREF_TAG_NAME}", name)
        getPreferenceEditor().putString("${number}_${PreferenceConstants.PREF_TAG_NUMBER}", name)
        getPreferenceEditor().putString("${number}_${PreferenceConstants.PREF_TAG_MESSAGE}", name)
        getPreferenceEditor().apply()
    }

    fun getAllContacts() {

    }
}