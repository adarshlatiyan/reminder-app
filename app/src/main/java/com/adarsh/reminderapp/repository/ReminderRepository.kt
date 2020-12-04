package com.adarsh.reminderapp.repository

import com.adarsh.reminderapp.DataState
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.data.local.ReminderDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReminderRepository @Inject constructor(private val reminderDao: ReminderDao) {
    suspend fun insertReminder(reminderModel: ReminderModel) {
        reminderDao.insertReminder(reminderModel)
    }

    suspend fun deleteReminder(reminderModel: ReminderModel) {
        reminderDao.deleteReminder(reminderModel)
    }

    fun getAllReminders() = flow {
        emit(DataState.Loading)
        try {
            reminderDao.getAllReminders().collect {
                emit(DataState.Success(it))
            }
        } catch (e : Exception) {
            emit(DataState.Failure(e))
        }
    }
}
