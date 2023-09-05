package com.suresh.nocontacts.ui.nocontacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suresh.nocontacts.constants.AppConstants
import com.suresh.nocontacts.databinding.FragmentNoContactsBinding
import com.suresh.nocontacts.listeners.ItemClickListener
import com.suresh.nocontacts.model.Contact
import com.suresh.nocontacts.ui.nocontacts.adapters.ContactsAdapter
import com.suresh.nocontacts.ui.viewcontact.ViewContactActivity
import com.suresh.nocontacts.utils.AppUtils

class NoContactsFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentNoContactsBinding? = null
    private val binding get() = _binding!!
    private lateinit var noContactsViewModel: NoContactsViewModel
    private lateinit var contacts: ArrayList<Contact>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        noContactsViewModel =
            ViewModelProvider(this)[NoContactsViewModel::class.java]

        _binding = FragmentNoContactsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        contacts = noContactsViewModel.getAllContacts(requireContext())
        showContactsList(contacts)
    }

    private fun showContactsList(contacts: ArrayList<Contact>) {
        val contactsAdapter = ContactsAdapter(this, contacts)
        binding.recyclerContacts.adapter = contactsAdapter
        binding.recyclerContacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMessageClick(number: String) {
        AppUtils.openContactInWhatsApp(requireContext(), number)
    }

    override fun onItemClick(number: String) {
        val intent = Intent(requireActivity(), ViewContactActivity::class.java)
        intent.putExtra(AppConstants.KEY_SELECTED_NUMBER, number)
        startActivity(intent)
    }
}