package com.app.sports.presentation.fragment.allLeagues

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.sports.data.remote.dto.Competitions
import com.app.sports.databinding.ItemCompetitionBinding

class AllLeaguesAdapter : ListAdapter<Competitions, AllLeaguesAdapter.MyHolder>(ItemDiff), Filterable {

    private var action: (Competitions) -> Unit = {}
    private lateinit var fullList: ArrayList<Competitions>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setData(list: List<Competitions>?) {
        submitList(list)
        fullList = ArrayList(list ?: emptyList())
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.itemBinding.data = item
            holder.itemView.setOnClickListener { action(item) }
        }
    }

    inner class MyHolder(val itemBinding: ItemCompetitionBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    private object ItemDiff : DiffUtil.ItemCallback<Competitions>() {
        override fun areItemsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
            return oldItem == newItem
        }
    }

    fun onItemClicked(action: (Competitions) -> Unit) {
        this.action = action
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterList = ArrayList<Competitions>()
            if (constraint.isNullOrEmpty()) filterList.addAll(fullList)
            else {
                fullList.map {
                    if (it.name?.contains(constraint.trim()) == true) filterList.add(it)
                }
            }
            return FilterResults().apply { values = filterList }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.values?.let {
                submitList(null)
                submitList(it as ArrayList<Competitions>)
            }
        }
    }
}
