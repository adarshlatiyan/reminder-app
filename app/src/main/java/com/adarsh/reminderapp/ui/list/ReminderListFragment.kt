package com.adarsh.reminderapp.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.R
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.FragmentReminderListBinding
import com.adarsh.reminderapp.ui.list.ReminderListFragmentViewModel.ReminderListState.GetListState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReminderListFragment : Fragment(R.layout.fragment_reminder_list) {
    companion object {
        private const val TAG = "ReminderListFragment"
    }

    private lateinit var binding: FragmentReminderListBinding
    private val adapter = ReminderListAdapter()

    private val viewModel: ReminderListFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReminderListBinding.bind(view)

        subscribeToViewModel()
        viewModel.setState(GetListState)

        initViews()
    }

    private fun subscribeToViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    showLoading()
                }
                is DataState.Success -> {
                    hideLoading()
                    adapter.submitList(it.data)
                }
                is DataState.Failure -> {
                    hideLoading()
                }
            }
        }
    }

    private fun initViews() {
        with(binding) {
            reminderList.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = this@ReminderListFragment.adapter
            }

            fab.setOnClickListener {
                navigateToEditScreen(ReminderModel("", "", -1))
            }
        }
    }

    private fun navigateToEditScreen(reminder: ReminderModel) {
        val action =
            ReminderListFragmentDirections.actionReminderListFragmentToEditReminderFragment(reminder)
        findNavController().navigate(action)
    }

    private fun showLoading() {

    }

    private fun hideLoading() {

    }
}