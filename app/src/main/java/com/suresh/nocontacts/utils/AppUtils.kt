package com.suresh.nocontacts.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

class AppUtils {
    companion object {
        fun openContactInWhatsApp(context: Context, number: String) {
            val uri =
                Uri.parse("https://api.whatsapp.com/send?phone=$number&text=")
            val sendIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(sendIntent)
        }
    }
}