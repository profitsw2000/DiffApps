package profitsw2000.diffapps.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import profitsw2000.diffapps.R
import profitsw2000.diffapps.presentation.fragments.map.view.MapsFragment
import profitsw2000.diffapps.presentation.fragments.map.view.MarkerListFragment

class MainActivity : AppCompatActivity(), MapsFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MapsFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun openMarkerListFragment() {
        val manager = supportFragmentManager

        manager.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, MarkerListFragment.newInstance())
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}