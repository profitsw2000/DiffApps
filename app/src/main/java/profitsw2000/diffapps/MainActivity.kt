package profitsw2000.diffapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
/*    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var job: Job? = null
    private var counter = 0*/
    private var isCounted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.carImageView.setOnClickListener {
            isCounted = !isCounted
            if (isCounted) mainViewModel.startCar()
            else mainViewModel.stopCar()
        }
        mainViewModel.carCoordinatesLiveData.observe(this@MainActivity) {
            binding.counterTextView.text = it.x.toString()
        }
    }

/*    private fun startJob() {
        job = scope.launch {
            while (isActive) {
                counter++
                binding.counterTextView.text = counter.toString()
                delay(100)
            }
        }
    }

    private fun stopJob() {
        job?.cancel()
        job = null
        counter = 0
    }*/
}