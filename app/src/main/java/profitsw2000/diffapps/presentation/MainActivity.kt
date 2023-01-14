package profitsw2000.diffapps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import profitsw2000.diffapps.R
import profitsw2000.diffapps.presentation.fragments.map.view.MapsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MapsFragment.newInstance())
            .commitAllowingStateLoss()
    }
}