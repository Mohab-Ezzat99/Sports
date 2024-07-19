package com.app.sports.presentation.fragment.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.sports.data.remote.dto.Seasons
import com.app.sports.databinding.ItemSeasonBinding

class SeasonAdapter : ListAdapter<Seasons, SeasonAdapter.MyHolder>(ItemDiff) {

    private var action: (Seasons) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.itemBinding.data = item
            holder.itemView.setOnClickListener { action(item) }
        }
    }

    inner class MyHolder(val itemBinding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private object ItemDiff : DiffUtil.ItemCallback<Seasons>() {
        override fun areItemsTheSame(oldItem: Seasons, newItem: Seasons): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Seasons, newItem: Seasons): Boolean {
            return oldItem == newItem
        }
    }

    fun onItemClicked(action: (Seasons) -> Unit) {
        this.action = action
    }
}
