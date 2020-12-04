package com.adarsh.reminderapp.ui.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.R
import com.adarsh.reminderapp.ui.list.ReminderListFragmentViewModel.ReminderListState.GetListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReminderListFragment : Fragment(R.layout.fragment_reminder_list) {
    companion object {
        private const val TAG = "ReminderListFragment"
    }

    private val viewModel: ReminderListFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()
        viewModel.setState(GetListState)
    }

    private fun subscribeToViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when(it) {
                is DataState.Loading -> {
                    // TODO: 04-12-2020 LOADING
                }
                is DataState.Success -> {
                    it.data.forEach { reminder ->
                        Log.d(TAG, "subscribeToViewModel: $reminder")
                    }
                }
                is DataState.Failure -> {
                    // TODO: 04-12-2020 FAILURE
                }
            }
        }
    }
}