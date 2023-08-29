package com.suresh.nocontacts.ui.calls.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.suresh.nocontacts.R
import com.suresh.nocontacts.model.CallLogRecord

class CallLogsAdapter(private val callLogs: ArrayList<CallLogRecord>) :
    Adapter<CallLogsAdapter.CallLogsViewHolder>() {

    class CallLogsViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textNumber: TextView = itemView.findViewById(R.id.textNumber)
        fun bindData(callLogRecord: CallLogRecord, position: Int) {
            textNumber.text = callLogRecord.number
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_call_log, parent, false)
        return CallLogsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return callLogs.size
    }

    override fun onBindViewHolder(holder: CallLogsViewHolder, position: Int) {
        holder.bindData(callLogs[position], position)
    }
}