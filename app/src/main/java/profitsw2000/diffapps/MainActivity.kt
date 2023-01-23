package profitsw2000.diffapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import profitsw2000.diffapps.databinding.ActivityMainBinding
import profitsw2000.diffapps.utils.PathCoordinates
import kotlin.math.atan

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var rotation = 0f
    private var x = 0f
    private var y = 0f
    private var step = 0
    private val path = arrayListOf<PathCoordinates>(
        PathCoordinates(10f,20f),
        PathCoordinates(20f,30f),
        PathCoordinates(25f,40f),
        PathCoordinates(30f,60f),
        PathCoordinates(40f,65f),
        PathCoordinates(50f,65f),
        PathCoordinates(60f,80f),
        PathCoordinates(70f,105f),
        PathCoordinates(70f,120f),
        PathCoordinates(70f,130f),
        PathCoordinates(80f,140f),
        PathCoordinates(90f,155f),
        PathCoordinates(100f,170f),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val display = windowManager.defaultDisplay
        val width = display.width
        val height = display.height
        binding.carImageView.x = x
        binding.carImageView.y = y
        binding.carImageView.setOnClickListener {
/*            rotation += 10f
            it.rotation = rotation%360*/
            val oldX = it.x
            val oldY = it.y
/*            x += 10f
            y += 10f*/
            x = path[step].x
            y = path[step].y
            if (step < path.size - 1)  {
                step++
                rotation = (((atan((oldY-y)/(oldX-x)))*180f)/3.14159f) + 90f
                it.rotation = rotation
                it.x = x
                it.y = y
                Log.d("VVV", "x: $x, y: $y, rotation: $rotation")
            }
        }
    }
}