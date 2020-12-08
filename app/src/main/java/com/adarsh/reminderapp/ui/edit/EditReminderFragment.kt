package com.adarsh.reminderapp.ui.edit

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adarsh.reminderapp.R
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.DatePickerLayoutBinding
import com.adarsh.reminderapp.databinding.FragmentEditReminderBinding
import com.adarsh.reminderapp.databinding.TimePickerLayoutBinding
import com.adarsh.reminderapp.util.DataState
import com.adarsh.reminderapp.util.getDateString
import com.adarsh.reminderapp.util.getTimeString
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditReminderFragment : Fragment(R.layout.fragment_edit_reminder) {
    companion object {
        private const val TAG = "EditReminderFragment"
    }

    private val args: EditReminderFragmentArgs by navArgs()

    private lateinit var binding: FragmentEditReminderBinding

    private val viewModel: EditReminderFragmentViewModel by viewModels()

    private val timePickerLayoutBinding by lazy { TimePickerLayoutBinding.inflate(layoutInflater) }
    private val datePickerLayoutBinding by lazy { DatePickerLayoutBinding.inflate(layoutInflater) }

    private val timePickerDialog by lazy {
        AlertDialog.Builder(requireContext()).create().apply {
            setView(timePickerLayoutBinding.root)
            setCanceledOnTouchOutside(true)
        }
    }
    private val datePickerDialog by lazy {
        AlertDialog.Builder(requireContext()).create().apply {
            setView(datePickerLayoutBinding.root)
            setCanceledOnTouchOutside(true)
        }
    }

    private val pickedCalendar = Calendar.getInstance()

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
                DataState.Loading -> {
                }
            }
        }
    }

    private fun initViews(binding: FragmentEditReminderBinding, reminder: ReminderModel?) {
        with(binding) {
            if (reminder != null) {
                etTitle.setText(reminder.title)
                etDescription.setText(reminder.description)
                pickedCalendar.timeInMillis = reminder.timeInMillis
            }

            floatingActionButton.setOnClickListener {
                val title = etTitle.text.toString()
                val description = etDescription.text.toString()

                if (validateInput()) {
                    val reminderModel = ReminderModel(
                        title,
                        description,
                        pickedCalendar.timeInMillis
                    ).apply { if (reminder != null) pk = reminder.pk }

                    viewModel.setState(
                        EditReminderFragmentViewModel.EditStateEvent.InsertEvent(reminderModel)
                    )
                }
            }

            ivCalendar.setOnClickListener { datePickerDialog.show() }
            tvDate.apply {
                text = getDateString(pickedCalendar)
                setOnClickListener { datePickerDialog.show() }
            }
            ivTime.setOnClickListener { timePickerDialog.show() }
            tvTime.apply {
                text = getTimeString(pickedCalendar)
                setOnClickListener { timePickerDialog.show() }
            }
        }

        with(timePickerLayoutBinding) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                timePicker.hour = pickedCalendar.get(Calendar.HOUR_OF_DAY)
                timePicker.minute = pickedCalendar.get(Calendar.MINUTE)
            } else {
                timePicker.currentHour = pickedCalendar.get(Calendar.HOUR_OF_DAY)
                timePicker.currentMinute = pickedCalendar.get(Calendar.MINUTE)
            }
            saveButton.setOnClickListener {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    setTime(timePicker.hour, timePicker.minute)
                } else {
                    setTime(timePicker.currentHour, timePicker.currentMinute)
                }
                binding.tvTime.text = getTimeString(pickedCalendar)
                timePickerDialog.dismiss()
            }
        }

        with(datePickerLayoutBinding) {
            with(datePicker) {
                minDate = Calendar.getInstance().timeInMillis
                updateDate(
                    pickedCalendar.get(Calendar.YEAR),
                    pickedCalendar.get(Calendar.MONTH),
                    pickedCalendar.get(Calendar.DAY_OF_MONTH)
                )
            }
            saveButton.setOnClickListener {
                with(datePicker) { setDate(dayOfMonth, month, year) }
                binding.tvDate.text = getDateString(pickedCalendar)
                datePickerDialog.dismiss()
            }
        }
    }

    private fun validateInput(): Boolean {
        var result = true
        with(binding) {
            if (etTitle.text.toString().isEmpty()) {
                etTitle.error = "Title can not be empty"
                result = false
            }
            if (etDescription.text.toString().isEmpty()) {
                etDescription.error = "Description can not be empty"
                result = false
            }
            if (pickedCalendar.time.before(Calendar.getInstance().time)) {
                Snackbar.make(binding.root, "Please select a time in the future", Snackbar.LENGTH_SHORT).show()
                result = false
            }
        }
        return result
    }

    private fun setDate(day: Int, month: Int, year: Int) {
        Log.d(TAG, "setDate: $day/$month/$year")
        pickedCalendar.apply {
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.MONTH, month)
            set(Calendar.YEAR, year)
        }
    }

    private fun setTime(hour: Int, minute: Int) {
        pickedCalendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        }
    }


}