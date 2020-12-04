package com.adarsh.reminderapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.ReminderItemBinding

class ReminderListAdapter : RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder(ReminderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun submitList(list: List<ReminderModel>) {
        differ.submitList(list)
    }

    private val diffCallback = object : DiffUtil.ItemCallback<ReminderModel>() {
        override fun areItemsTheSame(oldItem: ReminderModel, newItem: ReminderModel): Boolean {
            return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: ReminderModel, newItem: ReminderModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    class ReminderViewHolder(private val binding: ReminderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reminder: ReminderModel) {
            with(binding) {
                textView.text = reminder.title
            }
        }
    }

}