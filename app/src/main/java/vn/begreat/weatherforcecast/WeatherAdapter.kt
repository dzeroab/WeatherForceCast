package vn.begreat.weatherforcecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import vn.begreat.data.models.Weather
import vn.begreat.weatherforcecast.databinding.ItemWeatherBinding

interface OnWeatherClick {
    fun onWeatherClick(weather: Weather)
}

class WeatherAdapter(private val callback: OnWeatherClick) :
    RecyclerView.Adapter<WeatherViewHolder>() {

    private var weathers: MutableList<Weather> = mutableListOf()

    fun setWeathers(list: List<Weather>) {
        val diffCallback = WeatherDiffUtil(weathers, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        weathers.clear()
        weathers.addAll(list)
//        notifyDataSetChanged()
        diffResult.dispatchUpdatesTo(this)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return WeatherViewHolder(binding, callback)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        if (position < itemCount) {
            val weather = weathers[position]
            holder.bindData(weather)
        }

    }

    override fun getItemCount(): Int = weathers.size
}


class WeatherViewHolder(private val binding: ItemWeatherBinding, callback: OnWeatherClick) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener { _ ->
            binding.model?.let {
                callback.onWeatherClick(it)
            }
        }
    }


    fun bindData(weather: Weather) {
        binding.model = weather
        binding.executePendingBindings()
    }
}


class WeatherDiffUtil(
    private val oldList: List<Weather>,
    private val newList: List<Weather>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}