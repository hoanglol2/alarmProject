package com.example.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alarm.R
import com.example.alarm.model.AlarmModel
import kotlinx.android.synthetic.main.alarm_item.view.*

class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    inner class AlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var currentList: ArrayList<AlarmModel> = ArrayList()

    fun setList(list: List<AlarmModel>) {
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    fun setList(alarmModel: AlarmModel) {
        currentList.add(alarmModel)
        notifyItemChanged(currentList.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        return AlarmViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.alarm_item, parent, false)
        )
    }

    override fun getItemCount() = currentList.size

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = currentList[position]

        holder.itemView.apply {
            tvTimeHome.text = alarm.time
            tvDateHome.text = alarm.date
        }
    }
}

