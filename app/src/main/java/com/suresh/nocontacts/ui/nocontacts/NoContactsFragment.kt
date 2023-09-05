package com.suresh.nocontacts.ui.nocontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.suresh.nocontacts.databinding.FragmentNoContactsBinding
import com.suresh.nocontacts.model.Contact
import com.suresh.nocontacts.ui.nocontacts.adapters.ContactsAdapter

class NoContactsFragment : Fragment() {

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
        val contactsAdapter = ContactsAdapter(contacts)
        binding.recyclerContacts.adapter = contactsAdapter
        binding.recyclerContacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}