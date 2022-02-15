package com.example.ui_fragment_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.common.Holiday
import com.example.ui_fragment_recyclerview.databinding.HolidayItemBinding

class HolidayAdapter(
    private var holidayList: List<Holiday>
) : RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>() {

    inner class HolidayViewHolder(val binding: HolidayItemBinding) :
        RecyclerView.ViewHolder(binding.root)

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
        val date = "Date: ${holidayList[position].date}"
        holder.binding.holidayItem.text = date
    }

    override fun getItemCount(): Int {
        return holidayList.size
    }

    fun updateItems(newItems: List<Holiday>) {
        holidayList = newItems
        notifyDataSetChanged()
    }
}
