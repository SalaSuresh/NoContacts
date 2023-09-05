package com.suresh.nocontacts.ui.calls.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.suresh.nocontacts.R
import com.suresh.nocontacts.model.CallLogRecord
import com.suresh.nocontacts.listeners.ItemClickListener
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class CallLogsAdapter(
    private val callLogs: MutableList<CallLogRecord>,
    private val itemClickListener: ItemClickListener
) :
    Adapter<CallLogsAdapter.CallLogsViewHolder>() {

    class CallLogsViewHolder(itemView: View) : ViewHolder(itemView) {
        private val textNumber: TextView = itemView.findViewById(R.id.textNumber)
        private val textName: TextView = itemView.findViewById(R.id.textName)
        private val textDateAndDuration: TextView = itemView.findViewById(R.id.textMessage)
        private val imageWhatsApp: ImageView = itemView.findViewById(R.id.imageWhatsApp)
        fun bindData(callLogRecord: CallLogRecord, itemClickListener: ItemClickListener) {
            textNumber.text = callLogRecord.number
            textName.text =
                if (callLogRecord.number == callLogRecord.name || TextUtils.isEmpty(callLogRecord.name)) {
                    "Unknown Number"
                } else {
                    callLogRecord.name
                }
            textDateAndDuration.text =
                getDate(callLogRecord.date.toLong())
            imageWhatsApp.setOnClickListener {
                itemClickListener.onMessageClick(callLogRecord.number)
            }
        }

        private fun getDate(timestamp: Long): String {
            val instant = Instant.ofEpochMilli(timestamp)
            val localZoneId = ZoneId.systemDefault()
            val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma  MMMM dd yyyy")
            return dateTimeFormatter.format(instant.atZone(localZoneId))
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
        holder.bindData(callLogs[position], itemClickListener)
    }
}