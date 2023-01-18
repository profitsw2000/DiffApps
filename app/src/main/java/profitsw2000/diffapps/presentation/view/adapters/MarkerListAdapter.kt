package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker
import profitsw2000.diffapps.databinding.MarkerListItemViewBinding
import java.text.DecimalFormat

class MarkerListAdapter : RecyclerView.Adapter<MarkerListAdapter.ViewHolder>() {

    private var data: List<Marker> = arrayListOf()
    private lateinit var binding: MarkerListItemViewBinding

    fun setData(data: List<Marker>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = MarkerListItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(marker: Marker) {
            with(binding) {
                val df = DecimalFormat("##.####")
                val lat = marker.position.latitude
                val lon = marker.position.longitude
                markerTitleTextView.text = marker.title
                markerCoordinatesTextView.text = "Координаты: ${df.format(lat)} ${df.format(lon)}"
            }
        }
    }
}