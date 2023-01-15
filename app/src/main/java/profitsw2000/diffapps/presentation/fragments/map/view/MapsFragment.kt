package profitsw2000.diffapps.presentation.fragments.map.view

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import profitsw2000.diffapps.R

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val saintPetersburg = LatLng(59.9386, 30.3141)
        googleMap.addMarker(MarkerOptions().position(saintPetersburg).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(saintPetersburg))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    companion object {
        fun newInstance() = MapsFragment()
    }
}