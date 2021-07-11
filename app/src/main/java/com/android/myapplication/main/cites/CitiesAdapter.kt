package com.android.myapplication.main.cites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.data.model.City
import com.android.myapplication.databinding.ItemCitiesBinding

/**
 * @author at-hien.dao
 */
class CitiesAdapter : ListAdapter<City, CitiesAdapter.ItemViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemCitiesBinding =
            ItemCitiesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.onBind(getItem(position))
    }

    inner class ItemViewHolder(private val binding: ItemCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal fun onBind(city: City) {
            binding.apply {
                tvTitle.text = city.title
                tvLatLong.text = city.latt_long
                tvLocation.text = city.location_type
            }
        }
    }

}

class TaskDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.woeid == newItem.woeid
    }
}
