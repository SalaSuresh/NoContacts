package com.suresh.nocontacts.ui.calls

import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.suresh.nocontacts.databinding.FragmentCallsBinding
import com.suresh.nocontacts.model.CallLogRecord

class CallsFragment : Fragment() {

    private var _binding: FragmentCallsBinding? = null

    private val binding get() = _binding!!
    private lateinit var callsViewModel: CallsViewModel

    private var requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(), this::handlePermissionResult
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        callsViewModel =
            ViewModelProvider(this)[CallsViewModel::class.java]

        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonRequestPermission.setOnClickListener {
            val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS)
            with(intent) {
                data = Uri.fromParts("package", requireContext().packageName, null)
                addCategory(CATEGORY_DEFAULT)
                addFlags(FLAG_ACTIVITY_NEW_TASK)
                addFlags(FLAG_ACTIVITY_NO_HISTORY)
                addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            }
            startActivity(intent)
        }

        /*val textView: TextView = binding.textNoPermission
        callsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onStart() {
        super.onStart()
        requestPermissionLauncher.launch(android.Manifest.permission.READ_CALL_LOG)
    }

    private fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            getCallLogs()
            hideNoPermissionUI()
        } else {
            showNoPermissionUI()
        }
    }

    private fun hideNoPermissionUI() {
        binding.textNoPermission.visibility = View.GONE
        binding.buttonRequestPermission.visibility = View.GONE
    }

    private fun showNoPermissionUI() {
        binding.textNoPermission.visibility = View.VISIBLE
        binding.buttonRequestPermission.visibility = View.VISIBLE
    }

    private fun getCallLogs() {
        val callLogs = callsViewModel.readCallLogs(requireActivity())
        showCallLogsList(callLogs)
    }

    private fun showCallLogsList(callLogs: ArrayList<CallLogRecord>) {
        Log.d("test", "showCallLogsList() called with: callLogs = $callLogs")
        binding.recyclerCallLogs.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}