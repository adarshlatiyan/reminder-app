package com.adarsh.reminderapp.di

import android.content.Context
import androidx.room.Room
import com.adarsh.reminderapp.data.local.AppDb
import com.adarsh.reminderapp.data.local.ReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDb::class.java, AppDb.DB_NAME).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDb) : ReminderDao = db.reminderDao()
}