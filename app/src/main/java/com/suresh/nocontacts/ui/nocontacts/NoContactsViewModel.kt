package com.suresh.nocontacts.ui.nocontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is No Contacts Fragment"
    }
    val text: LiveData<String> = _text
}