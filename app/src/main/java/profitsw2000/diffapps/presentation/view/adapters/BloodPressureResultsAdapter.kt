package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.HourBloodPressureResultListItemViewBinding
import profitsw2000.diffapps.model.BloodPressure
import java.text.SimpleDateFormat
import java.util.*

class BloodPressureResultsAdapter(private val data: List<BloodPressure>) : RecyclerView.Adapter<BloodPressureResultsAdapter.ViewHolder>() {

    private lateinit var binding: HourBloodPressureResultListItemViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HourBloodPressureResultListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(bloodPressure: BloodPressure, position: Int) {

            setBackground(position)
            val time = bloodPressure.date?.let { Date(it) }
            val sdf = SimpleDateFormat("HH:mm")

            with(binding) {
                measureTimeTextView.text = sdf.format(time)
                highPressureTextView.text = bloodPressure.highPressure.toString()
                lowPressureTextView.text = bloodPressure.lowPressure.toString()
                pulseTextView.text = bloodPressure.pulse.toString()
            }
        }
    }

    private fun setBackground(position: Int) {
        when(position % 4) {
            0 -> binding.hourBloodPressureResultListItemViewRootLayout.setBackgroundResource(R.drawable.yellow_gradient_background)
            1 -> binding.hourBloodPressureResultListItemViewRootLayout.setBackgroundResource(R.drawable.green_gradient_background)
            2 -> binding.hourBloodPressureResultListItemViewRootLayout.setBackgroundResource(R.drawable.pink_gradient_background)
            3 -> binding.hourBloodPressureResultListItemViewRootLayout.setBackgroundResource(R.drawable.blue_gradient_background)
        }
    }
}