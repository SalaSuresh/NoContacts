package com.suresh.nocontacts.ui.nocontacts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.suresh.nocontacts.R
import com.suresh.nocontacts.listeners.ItemClickListener
import com.suresh.nocontacts.model.Contact

class ContactsAdapter(
    private var itemClickListener: ItemClickListener,
    private var contacts: ArrayList<Contact>
) :
    Adapter<ContactsAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView: View) : ViewHolder(itemView) {
        var textName: TextView = itemView.findViewById(R.id.textName)
        var textNumber: TextView = itemView.findViewById(R.id.textNumber)
        var textMessage: TextView = itemView.findViewById(R.id.textMessage)
        var imageWhatsApp: ImageView = itemView.findViewById(R.id.imageWhatsApp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.textName.text = contact.name
        holder.textNumber.text = contact.number
        holder.textMessage.text = contact.message
        holder.imageWhatsApp.setOnClickListener {
            itemClickListener.onMessageClick(contact.number)
        }
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(contact.number)
        }
    }
}