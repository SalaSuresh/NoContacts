package com.suresh.nocontacts.ui.calls.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.suresh.nocontacts.model.CallLogRecord

class CallLogsAdapter(val callLogs: ArrayList<CallLogRecord>): Adapter<CallLogsAdapter.CallLogsViewHolder>() {

    class CallLogsViewHolder(itemView: View) : ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return callLogs.size
    }

    override fun onBindViewHolder(holder: CallLogsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}