package profitsw2000.diffapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import profitsw2000.diffapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var job: Job? = null
    private var counter = 0
    private var isCounted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.carImageView.setOnClickListener {
            isCounted = !isCounted
            if (isCounted) startJob()
            else stopJob()
        }
    }

    private fun startJob() {
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
    }
}