package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.Marker
import profitsw2000.diffapps.databinding.MarkerListItemViewBinding
import java.text.DecimalFormat

class MarkerListAdapter(val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MarkerListAdapter.ViewHolder>() {

    private var data: ArrayList<Marker> = arrayListOf()
    private lateinit var binding: MarkerListItemViewBinding

    fun setData(data: ArrayList<Marker>) {
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
        holder.bind(data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(marker: Marker, position: Int) {
            with(binding) {
                val df = DecimalFormat("##.####")
                val lat = marker.position.latitude
                val lon = marker.position.longitude
                markerNameTextView.text = marker.title
                markerLatitudeTextView.text = "${df.format(lat)}"
                markerLongitudeTextView.text = "${df.format(lon)}"

                deleteMarkerTextView.setOnClickListener {
                    onItemClickListener.onDeleteClick(position)
                }

                editMarkerTextView.setOnClickListener {
                    onItemClickListener.onEditClick(position)
                }
            }
        }
    }
}