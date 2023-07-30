package com.nandini.android.workoutapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nandini.android.workoutapp.databinding.ActivityHistoryBinding
import com.nandini.android.workoutapp.databinding.ItemHistoryRowBinding

class HistoryAdapter(private var items:ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemHistoryRowBinding):RecyclerView.ViewHolder(binding.root){
        var llItemRow=binding.llItemRow
        var tvPosition=binding.tvPosition
        var tvItem=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String=items.get(position)
        holder.tvPosition.text=(position+1).toString()
        holder.tvItem.text=date
    }

    override fun getItemCount(): Int {
        return items.size
    }


}