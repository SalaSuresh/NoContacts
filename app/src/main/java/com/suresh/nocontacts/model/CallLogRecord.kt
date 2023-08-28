package com.suresh.nocontacts.model

data class CallLogRecord(
    val id: Int,
    val number: String,
    val name: String,
    val type: Int,
    val date: String,
    val duration: String
)

