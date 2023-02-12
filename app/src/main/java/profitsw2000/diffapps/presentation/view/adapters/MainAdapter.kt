package profitsw2000.diffapps.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import profitsw2000.diffapps.databinding.DailyBloodPressureResultListItemViewBinding
import profitsw2000.diffapps.model.DayBloodPressure
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private var data: List<DayBloodPressure> = arrayListOf()
    private lateinit var binding: DailyBloodPressureResultListItemViewBinding

    fun setData (data: List<DayBloodPressure>) {
        this.data = data
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
        fun bind(dayBloodPressure: DayBloodPressure) = with(binding) {

            val date = Date(dayBloodPressure.date)
            val sdf = SimpleDateFormat("d MMMM", Locale("RU"))
            dateTextView.text = sdf.format(date)
            val layoutManager = LinearLayoutManager(hourBloodPressureResultRecyclerView.context, LinearLayoutManager.VERTICAL, false)

            hourBloodPressureResultRecyclerView.apply {
                setLayoutManager(layoutManager)
                adapter = BloodPressureResultsAdapter(dayBloodPressure.bloodPressureList)
                setRecycledViewPool(viewPool)
            }
        }
    }
}