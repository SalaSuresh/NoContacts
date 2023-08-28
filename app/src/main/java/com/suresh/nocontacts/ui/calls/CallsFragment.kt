package com.suresh.nocontacts.ui.calls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.suresh.nocontacts.databinding.FragmentNoContactsBinding
import com.suresh.nocontacts.model.CallLogRecord

class CallsFragment : Fragment() {

    private var _binding: FragmentNoContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var callsViewModel:CallsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        callsViewModel =
            ViewModelProvider(this)[CallsViewModel::class.java]

        _binding = FragmentNoContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNoContacts
        callsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onResume() {
        super.onResume()
//        val requestPermissionLauncher = requireActivity().registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted ->
//            if (isGranted) {
//                // PERMISSION GRANTED
//            } else {
//                // PERMISSION NOT GRANTED
//            }
//        }
//        requestPermissionLauncher.launch(android.Manifest.permission.READ_CALL_LOG)
//        val callLogs = callsViewModel.readCallLogs(requireActivity())
//        showCallLogsList(callLogs)
    }

    private fun showCallLogsList(callLogs: ArrayList<CallLogRecord>) {
        Log.d("test", "showCallLogsList() called with: callLogs = $callLogs")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}