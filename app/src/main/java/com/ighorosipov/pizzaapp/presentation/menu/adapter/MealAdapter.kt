package com.ighorosipov.pizzaapp.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ighorosipov.pizzaapp.databinding.ItemMealBinding
import com.ighorosipov.pizzaapp.domain.model.Meal

class MealAdapter : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    private var onClickListener: OnClickListener? = null
    private var meals = emptyList<Meal>()

    class MealViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        with(holder.binding) {

            Glide.with(holder.itemView.context)
                .load(meals[holder.adapterPosition].thumbnail)
                .fitCenter()
                .into(mealImage)

            title.text = meals[holder.adapterPosition].title
            description.text = meals[holder.adapterPosition].id
        }

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onMealClick(holder.adapterPosition, meals[holder.adapterPosition])
            }
        }

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onMealClick(position: Int, meal: Meal)
    }

    fun setList(newList: List<Meal>) {
        val diffUtil = MealDiff(meals, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        diffResult.dispatchUpdatesTo(this)
        meals = newList
    }

    override fun getItemCount(): Int {
        return meals.size
    }

}