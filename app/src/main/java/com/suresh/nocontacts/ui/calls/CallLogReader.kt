package com.suresh.nocontacts.ui.calls

import android.content.Context
import android.net.Uri
import android.provider.CallLog
import android.util.Log
import com.suresh.nocontacts.model.CallLogRecord

class CallLogReader(private val context: Context) {
    private val callLogs = ArrayList<CallLogRecord>()
    fun readCallLogs(): ArrayList<CallLogRecord> {
        val contentResolver = context.contentResolver
        val callLogUri = Uri.parse("content://call_log/calls")
        val cursor = contentResolver.query(callLogUri, null, null, null, null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls._ID))
                val number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME))
                val type = cursor.getInt(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE))
                val duration =
                    cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION))

                Log.d("test", "Call log id: $id")
                Log.d("test", "Call number: $number")
                Log.d("test", "Call name: $name")
                Log.d("test", "Call type: $type")
                Log.d("test", "Call date: $date")
                Log.d("test", "Call duration: $duration")

                val call =
                    CallLogRecord(id, number, name, type, date, duration)
                callLogs.add(call)
            }
            cursor.close()
        }

        return callLogs
    }
}
