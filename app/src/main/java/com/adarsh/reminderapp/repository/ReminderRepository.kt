package com.adarsh.reminderapp.repository

import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.data.local.ReminderDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(private val reminderDao: ReminderDao) {
    suspend fun insertReminder(reminderModel: ReminderModel) {
        reminderDao.insertReminder(reminderModel)
    }

    suspend fun deleteReminder(reminderModel: ReminderModel) {
        reminderDao.deleteReminder(reminderModel)
    }

    fun getAllReminders() = flow<DataState<List<ReminderModel>>> {
        reminderDao.getAllReminders().collect {
            emit(DataState.Success(it))
        }
    }
}
