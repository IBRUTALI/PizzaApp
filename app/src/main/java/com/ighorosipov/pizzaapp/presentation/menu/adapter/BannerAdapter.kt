package com.ighorosipov.pizzaapp.presentation.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ighorosipov.pizzaapp.R
import com.ighorosipov.pizzaapp.databinding.ItemBannerBinding

class BannerAdapter: RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private val itemCount = 3

    class BannerViewHolder(val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding =
            ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        with(holder.binding) {
            bannerImage.setImageResource(R.drawable.im_1)
        }

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener?.onBannerClick(position)
            }
        }

    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onBannerClick(position: Int)
    }

    override fun getItemCount(): Int {
        return itemCount
    }
}
