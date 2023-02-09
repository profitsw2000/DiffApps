package profitsw2000.diffapps.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure
import profitsw2000.diffapps.presentation.view.adapters.MainAdapter
import java.util.Date

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var adapter = MainAdapter()
    private val bloodPressure = BloodPressure(
        id = "202302081034",
        date = Date().time,
        highPressure = 130,
        lowPressure = 70,
        pulse = 60
    )

    private val bloodPressureList = arrayListOf(
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure,
        bloodPressure
    )

    private val dayBloodPressure = DayBloodPressure(
        id = "20230208",
        date = Date().time,
        bloodPressureList
    )

    private val dayBloodPressureList = arrayListOf(
        dayBloodPressure,
        dayBloodPressure,
        dayBloodPressure
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter.setData(dayBloodPressureList)
        adapter.notifyDataSetChanged()
        binding.bloodPressureResultRecyclerView.adapter = adapter
    }
}