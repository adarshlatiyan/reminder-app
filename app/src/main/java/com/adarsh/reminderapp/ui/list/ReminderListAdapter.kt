package com.adarsh.reminderapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.reminderapp.data.ReminderModel
import com.adarsh.reminderapp.databinding.ReminderItemBinding
import javax.inject.Inject

class ReminderListAdapter @Inject constructor() :
    RecyclerView.Adapter<ReminderListAdapter.ReminderViewHolder>() {

    var interactionListener: InteractionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder(
            ReminderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interactionListener
        )
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

    class ReminderViewHolder(
        private val binding: ReminderItemBinding,
        private val listener: InteractionListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { listener?.onItemClick(adapterPosition) }
            binding.ivDelete.setOnClickListener { listener?.onItemDeleteClicked(adapterPosition) }
        }

        fun bind(reminder: ReminderModel) {
            with(binding) {
                tvTitle.text = reminder.title
                tvDescription.text = reminder.description
            }
        }
    }

    interface InteractionListener {
        fun onItemClick(position: Int)
        fun onItemDeleteClicked(position: Int)
    }

}