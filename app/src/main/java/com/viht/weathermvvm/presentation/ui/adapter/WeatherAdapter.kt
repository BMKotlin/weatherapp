package com.viht.weathermvvm.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.viht.weathermvvm.R
import com.viht.weathermvvm.databinding.ItemWeatherBinding
import com.viht.weathermvvm.domain.model.Weather
import com.viht.weathermvvm.presentation.utils.setSafeOnClickListener

class WeatherAdapter(private val onItemClick: (weather: Weather) -> Unit) :
    ListAdapter<Weather, WeatherAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        currentList.getOrNull(position)?.let {
            with(holder) {
                val resource = binding.root.resources
                binding.tvDate.text = resource.getString(R.string.text_date, it.date)
                binding.tvTempAvg.text =
                    resource.getString(R.string.text_avg_temperature, it.avgTemperature)
                binding.tvPressure.text = resource.getString(R.string.text_pressure, it.pressure)
                binding.tvHumidity.text = resource.getString(R.string.text_humidity, it.humidity)
                binding.tvDescription.text =
                    resource.getString(R.string.text_description, it.description)
            }
        }
    }

    override fun getItemCount(): Int = currentList.size

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Weather>() {
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather) =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather) =
                oldItem.areSameContent(newItem)
        }
    }


    inner class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val binding = ItemWeatherBinding.bind(view)

        init {
            itemView.setSafeOnClickListener {
                onItemClick.invoke(currentList[adapterPosition])
            }
        }
    }

}