package com.nandini.android.workoutapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nandini.android.workoutapp.databinding.ActivityExerciseBinding
import com.nandini.android.workoutapp.databinding.ItemExcerciseStatusBinding

class ExerciseStatusAdapter (val items:ArrayList<ExerciseModel>):RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>(){


    class ViewHolder(binding: ItemExcerciseStatusBinding):RecyclerView.ViewHolder(binding.root){
        val tvItem=binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExcerciseStatusBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseModel=items[position]
        holder.tvItem.text=model.getId().toString()

        when{
            model.getIsSelected()->{
                holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_grey_background_selected_orange)
                holder.tvItem.setTextColor(Color.parseColor("#000000"))}
            model.getIsCompleted()->{
                holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_grey_background_selected_orange)
                holder.tvItem.setTextColor(Color.parseColor("#000000"))}
            else->{
                holder.tvItem.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.item_circular_color_grey_background_selected)
                holder.tvItem.setTextColor(Color.parseColor("#FB7E11"))
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}