package profitsw2000.diffapps.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.model.BloodPressure
import profitsw2000.diffapps.model.DayBloodPressure
import profitsw2000.diffapps.presentation.view.adapters.MainAdapter
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var adapter = MainAdapter()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addMeasureFab.setOnClickListener {
            showEditDialog()
        }

        val observer = Observer<List<DayBloodPressure>> {
            renderData(it)
        }
        mainViewModel.bpMeasurementsLiveData.observe(this, observer)
        val writeObserver = Observer<Boolean> {
            if (it) {
                Toast.makeText(this, "Write data is successful!",Toast.LENGTH_SHORT).show()
            } else Toast.makeText(this, "Error!",Toast.LENGTH_SHORT).show()
        }
        mainViewModel.firebaseWriteResultLiveData.observe(this, writeObserver)
        mainViewModel.getAllBPMeasurementResults()
    }

    private fun renderData(dayBloodPressureList: List<DayBloodPressure>) {
        binding.bloodPressureResultRecyclerView.adapter = adapter
        adapter.setData(dayBloodPressureList)
        adapter.notifyDataSetChanged()
        binding.bloodPressureResultRecyclerView.scrollToPosition(dayBloodPressureList.size - 1)
    }

    private fun showEditDialog() {
        val view: View = layoutInflater.inflate(R.layout.add_blood_pressure_measurment_result_dialog, null)

        this.let {
            AlertDialog.Builder(it)
                .setTitle("Введите результат измерения давления")
                .setPositiveButton("OK")  { _, _ ->
                    checkInput(view)
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .setView(view)
                .create()
                .show()
        }
    }

    private fun checkInput(view: View) {
        val highPressureEditText = view.findViewById<EditText>(R.id.high_pressure_edit_text).text.toString()
        val lowPressureEditText = view.findViewById<EditText>(R.id.low_pressure_edit_text).text.toString()
        val pulse = view.findViewById<EditText>(R.id.pulse_edit_text).text.toString()

        if (highPressureEditText.trim().isNotEmpty() &&
                (highPressureEditText.trim().toInt() in 1..249) &&
                lowPressureEditText.trim().isNotEmpty() &&
                (lowPressureEditText.trim().toInt() in 1..249) &&
                pulse.trim().isNotEmpty() &&
                (pulse.trim().toInt() in 1..249) ) {

            val date = Date().time
            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            val id = sdf.format(date)

            val bloodPressure = BloodPressure (
                id = id,
                date = date,
                highPressure = highPressureEditText.trim().toInt(),
                lowPressure = lowPressureEditText.trim().toInt(),
                pulse = pulse.trim().toInt()
            )
            mainViewModel.addBPMeasurement(bloodPressure)
        } else {
            showErrorDialog()
        }
    }

    private fun showErrorDialog() {
        this.let {
            AlertDialog.Builder(it)
                .setTitle("Ошибка")
                .setMessage("Поля ввода не должны быть пустыми и содержать только числа от 0 до 250")
                .setPositiveButton("OK")  { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }
}