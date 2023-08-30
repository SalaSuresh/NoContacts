package com.suresh.nocontacts.ui.calls

import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suresh.nocontacts.model.CallLogRecord
class CallsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Calls Fragment"
    }
    val text: LiveData<String> = _text

    fun readCallLogs(requireActivity: FragmentActivity): ArrayList<CallLogRecord> {

        val hasPermission = ContextCompat.checkSelfPermission(
            requireActivity,
            android.Manifest.permission.READ_CALL_LOG
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            // Request the permission
//            ActivityCompat.requestPermissions(
//                requireActivity,
//                arrayOf(android.Manifest.permission.READ_CALL_LOG),
//                1
//            )

            return ArrayList()
        } else {
            // We already have the permission
            return CallLogReader(requireActivity).readCallLogs()
        }
    }


}