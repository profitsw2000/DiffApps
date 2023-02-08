package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.databinding.DailyBloodPressureResultListItemViewBinding
import profitsw2000.diffapps.databinding.HourBloodPressureResultListItemViewBinding
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure
import java.text.SimpleDateFormat
import java.util.*

class BloodPressureResultsAdapter : RecyclerView.Adapter<BloodPressureResultsAdapter.ViewHolder>() {

    private var data: List<BloodPressure> = arrayListOf()
    private lateinit var binding: HourBloodPressureResultListItemViewBinding

    fun setData (data: List<BloodPressure>) {
        this.data = data
    }

    fun getData() : List<BloodPressure> {
        return data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HourBloodPressureResultListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bloodPressure: BloodPressure) {
            val time = Date(bloodPressure.date)
            val sdf = SimpleDateFormat("HH:mm")

            with(binding) {
                measureTimeTextView.text = sdf.format(time)
                highPressureTextView.text = bloodPressure.highPressure.toString()
                lowPressureTextView.text = bloodPressure.lowPressure.toString()
                pulseTextView.text = bloodPressure.pulse.toString()
            }
        }
    }
}