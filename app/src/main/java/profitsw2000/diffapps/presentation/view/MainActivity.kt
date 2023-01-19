package profitsw2000.diffapps.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import profitsw2000.diffapps.R
import profitsw2000.diffapps.presentation.view.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun openDetailsFragment(filmId: String) {

    }
}