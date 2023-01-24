package profitsw2000.diffapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPathAmplitudeAndLength()
        binding.carImageView.setOnClickListener {
           mainViewModel.startCar()
        }

        mainViewModel.carCoordinatesLiveData.observe(this@MainActivity) {
            binding.carImageView.x = it.x
            binding.carImageView.y = it.y
            binding.carImageView.rotation = it.orientation
        }
    }

    private fun setPathAmplitudeAndLength() {
        val displayHeight = windowManager.defaultDisplay.height
        val displayWidth = windowManager.defaultDisplay.width
        val carWidth = resources.getDrawable(R.drawable.car_icon).minimumWidth
        val carLength = resources.getDrawable(R.drawable.car_icon).minimumHeight
        val pathAmplitude = (displayWidth - carWidth)/2
        val pathLength = (displayHeight - carLength)

        mainViewModel.setPathAmplitudeAndLength(pathAmplitude, pathLength)
    }
}