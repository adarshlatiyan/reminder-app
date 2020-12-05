package com.adarsh.reminderapp.ui.edit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.R
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.FragmentEditReminderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditReminderFragment : Fragment(R.layout.fragment_edit_reminder) {
    companion object {
        private const val TAG = "EditReminderFragment"
    }

    private val timeInMillis = 0L
    private val args: EditReminderFragmentArgs by navArgs()

    private lateinit var binding: FragmentEditReminderBinding

    private lateinit var title: String
    private lateinit var description: String

    private val viewModel: EditReminderFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()

        binding = FragmentEditReminderBinding.bind(view)
        initViews(binding, args.reminder)
    }

    private fun subscribeToViewModel() {
        viewModel.dataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    findNavController().popBackStack()
                }
                is DataState.Failure -> {
                    Log.e(TAG, "subscribeToViewModel: ", it.exception)
                }
                DataState.Loading -> { }
            }
        }
    }

    fun getReminderModel(): ReminderModel {
        args.reminder?.let {
            it.title = title
            it.description = description
            return it
        }
        return ReminderModel(title, description, timeInMillis)
    }

    private fun initViews(binding: FragmentEditReminderBinding, reminder: ReminderModel?) {
        with(binding) {
            if (reminder != null) {
                etTitle.setText(reminder.title)
                etDescription.setText(reminder.description)
            }

            floatingActionButton.setOnClickListener {
                title = etTitle.text.toString()
                description = etDescription.text.toString()

                viewModel.setState(
                    EditReminderFragmentViewModel.EditStateEvent.InsertEvent(getReminderModel())
                )
            }
        }
    }
}