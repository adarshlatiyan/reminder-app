package com.adarsh.reminderapp.ui.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.R
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.FragmentEditReminderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditReminderFragment : Fragment(R.layout.fragment_edit_reminder) {
    private lateinit var binding: FragmentEditReminderBinding

    private lateinit var title: String
    private lateinit var description: String

    private val viewModel: EditReminderFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

        binding = FragmentEditReminderBinding.bind(view)
        initViews(binding)
    }

    private fun subscribeToViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun initViews(binding: FragmentEditReminderBinding) {
        with(binding) {
            floatingActionButton.setOnClickListener {
                title = etTitle.text.toString()
                description = etDescription.text.toString()

                viewModel.setState(
                    EditReminderFragmentViewModel.EditStateEvent.InsertEvent(
                        ReminderModel(title, description, 0)
                    )
                )
            }
        }
    }
}