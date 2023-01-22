package profitsw2000.diffapps.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import profitsw2000.diffapps.R
import profitsw2000.diffapps.presentation.view.fragments.DetailsFragment
import profitsw2000.diffapps.presentation.view.fragments.DetailsFragment.Companion.BUNDLE_EXTRA
import profitsw2000.diffapps.presentation.view.fragments.MainFragment

class MainActivity : AppCompatActivity(), MainFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .commitAllowingStateLoss()
    }

    override fun openDetailsFragment(id: Int) {
        val bundle = Bundle().apply {
            putInt(BUNDLE_EXTRA, id)
        }
        val manager = supportFragmentManager

        manager.let {
            manager.beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}