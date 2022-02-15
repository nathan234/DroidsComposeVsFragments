package com.example.droidscomposesample.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data_networking.HolidayDTO
import com.example.droidscomposesample.databinding.HolidayItemBinding

class HolidayAdapter(
    private var holidayList: List<HolidayDTO>
) : RecyclerView.Adapter<HolidayAdapter.HolidayViewHolder>() {

    inner class HolidayViewHolder(val binding: HolidayItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HolidayAdapter.HolidayViewHolder {
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

    fun updateItems(newItems: List<HolidayDTO>) {
        holidayList = newItems
        notifyDataSetChanged()
    }
}
