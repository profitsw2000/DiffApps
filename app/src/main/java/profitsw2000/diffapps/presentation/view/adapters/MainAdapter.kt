package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.databinding.DailyBloodPressureResultListItemViewBinding
import profitsw2000.diffapps.model.DayBloodPressure
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var data: List<DayBloodPressure> = arrayListOf()
    private lateinit var binding: DailyBloodPressureResultListItemViewBinding

    fun setData (data: List<DayBloodPressure>) {
        this.data = data
    }

    fun getData() : List<DayBloodPressure> {
        return data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DailyBloodPressureResultListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun bind(dayBloodPressure: DayBloodPressure) {

            val time = Date(dayBloodPressure.date)
            val sdf = SimpleDateFormat("d MMM", Locale("RU"))

            val bloodPressureResultsAdapter = BloodPressureResultsAdapter()
            bloodPressureResultsAdapter.setData(dayBloodPressure.bloodPressureList)
            bloodPressureResultsAdapter.notifyDataSetChanged()

            with(binding) {
                dateTextView.text = dayBloodPressure.date.toString()
                hourBloodPressureResultRecyclerView.adapter = bloodPressureResultsAdapter
            }
        }
    }
}