package com.adarsh.reminderapp.ui.edit

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.adarsh.reminderapp.repository.ReminderRepository

class EditReminderFragmentViewModel @ViewModelInject constructor(
    private val repository: ReminderRepository,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    // TODO: 04-12-2020 CREATE VM
}