package com.ighorosipov.pizzaapp.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ighorosipov.pizzaapp.databinding.ItemCategoryBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var categories = listOf(
        Category.Pizza(),
        Category.Combo(),
        Category.Dessert(),
        Category.Drinks(),
    )

    private var currentCategory = categories[0]

    class CategoryViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val isSelectedTag = categories[position] == currentCategory
        with(holder.binding) {
            title.text = holder.itemView.context.resources.getString(categories[position].resId)
        }

        holder.itemView.isSelected = isSelectedTag

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onCategoryClick(position, categories[position])
            }
        }

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onCategoryClick(position: Int, category: Category)
    }

    fun updateCategory(category: Category) {
        val lastTagPosition = categories.indexOf(currentCategory)
        val currentTagPosition = categories.indexOf(category)
        currentCategory = category
        notifyItemChanged(currentTagPosition)
        notifyItemChanged(lastTagPosition, false)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}