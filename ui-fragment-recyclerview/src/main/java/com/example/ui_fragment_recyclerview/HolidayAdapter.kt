package com.example.ui_fragment_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.Holiday
import com.example.ui_fragment_recyclerview.databinding.HolidayItemBinding

class HolidayAdapter(
    private val onClick: (Holiday) -> Unit
) : ListAdapter<Holiday, HolidayAdapter.HolidayViewHolder>(HolidayDiffCallback) {

    class HolidayViewHolder(val binding: HolidayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(holiday: Holiday) {
                val date = "Date: ${holiday.date}"
                binding.holidayItem.text = date
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HolidayViewHolder {
        val itemBinding = HolidayItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HolidayViewHolder(binding = itemBinding)
    }

    override fun onBindViewHolder(holder: HolidayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object HolidayDiffCallback : DiffUtil.ItemCallback<Holiday>() {
    override fun areItemsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Holiday, newItem: Holiday): Boolean {
        return oldItem.date == newItem.date
    }
}
