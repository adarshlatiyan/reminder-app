package com.adarsh.reminderapp.ui.edit

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.adarsh.reminderapp.util.DataState
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.repository.ReminderRepository
import com.adarsh.reminderapp.util.AlarmHelper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class EditReminderFragmentViewModel @ViewModelInject constructor(
    private val repository: ReminderRepository,
    private val alarmHelper: AlarmHelper,
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _dataState = MutableLiveData<DataState<Unit>>()
    val dataState: LiveData<DataState<Unit>> get() = _dataState

    fun setState(event: EditStateEvent) {
        viewModelScope.launch {
            when (event) {
                is EditStateEvent.InsertEvent -> {
                    repository.insertReminder(event.reminder).collect {
                        _dataState.value = it
                    }
                    val cal = Calendar.getInstance().apply { timeInMillis = event.reminder.timeInMillis }
                    alarmHelper.setAlarm(cal, event.reminder.pk)
                }
                else -> {
                }
            }
        }
    }

    sealed class EditStateEvent {
        data class InsertEvent(val reminder: ReminderModel) : EditStateEvent()
        object None : EditStateEvent()
    }
}