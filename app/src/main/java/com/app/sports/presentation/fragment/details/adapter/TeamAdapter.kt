package com.app.sports.presentation.fragment.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.sports.R
import com.app.sports.data.local.entity.TeamEntity
import com.app.sports.databinding.ItemTeamBinding
import com.app.sports.utils.MyUtils.loadImg

class TeamAdapter : ListAdapter<TeamEntity, TeamAdapter.MyHolder>(ItemDiff) {

    private var action: (TeamEntity, Int) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.itemBinding.data = item
            holder.itemBinding.ivImg.loadImg(item.crest)
            holder.itemBinding.ivFav.setImageResource(getFavResId(item.isFavourite))

            holder.itemBinding.ivFav.setOnClickListener { action(item, position) }
        }
    }

    private fun getFavResId(isFav: Boolean): Int {
        return if (isFav) R.drawable.ic_fav_fill
        else R.drawable.ic_fav
    }

    inner class MyHolder(val itemBinding: ItemTeamBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private object ItemDiff : DiffUtil.ItemCallback<TeamEntity>() {
        override fun areItemsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TeamEntity, newItem: TeamEntity): Boolean {
            return oldItem == newItem
        }
    }

    fun onFavClicked(action: (TeamEntity, Int) -> Unit) {
        this.action = action
    }
}
