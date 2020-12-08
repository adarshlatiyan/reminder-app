package com.adarsh.reminderapp.repository

import android.util.Log
import com.adarsh.reminderapp.util.DataState
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.data.local.ReminderDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(private val reminderDao: ReminderDao) {
    fun insertReminder(reminderModel: ReminderModel) : Flow<DataState<Unit>> = flow {
        emit(DataState.Loading)
        try {
            reminderDao.insertReminder(reminderModel)
            emit(DataState.Success(Unit))
        } catch (e: Exception) {
            Log.e("TAG", "insertReminder: ", e)
            emit(DataState.Failure(e))
        }
    }

    fun deleteReminder(reminderModel: ReminderModel) = flow {
        emit(DataState.Loading)
        try {
            reminderDao.deleteReminder(reminderModel)
            emit(DataState.Success(Unit))
        } catch (e: Exception) {
            emit(DataState.Failure(e))
        }
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
