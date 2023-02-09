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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //adapter.setData(dayBloodPressureList)
        adapter.notifyDataSetChanged()
        binding.bloodPressureResultRecyclerView.adapter = adapter
    }
}