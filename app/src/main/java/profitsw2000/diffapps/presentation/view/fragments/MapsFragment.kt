package profitsw2000.diffapps.presentation.view.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import profitsw2000.diffapps.R
import profitsw2000.diffapps.databinding.FragmentMapsBinding
import profitsw2000.diffapps.presentation.viewmodel.MapViewModel
import java.util.function.Consumer

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_CODE = 10
    private val controller by lazy { activity as Controller }
    private lateinit var map: GoogleMap
    private val mapViewModel: MapViewModel by viewModel()
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        setMarkersOnMap()
        map.setOnMapLongClickListener { latLng ->
            addMarkerToArray(latLng)
        }

        activateMyLocation(map)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity !is Controller) {
            throw IllegalStateException("Activity должна наследоваться от Controller")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        checkPermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_map_fragment,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.marker_list -> {
                controller.openMarkerListFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setMarkersOnMap() {
        mapViewModel.getMarkerList().observe(viewLifecycleOwner) {
            map.clear()
            if (it != null && it.isNotEmpty()) {
                for (marker in it) {
                    map.addMarker(MarkerOptions()
                        .position(marker.position)
                        .title(marker.title)
                    )
                }
            }
        }
    }

    private fun addMarkerToArray(latLng: LatLng) {
        map.addMarker(MarkerOptions().position(latLng))?.let {
            mapViewModel.addMarker(it)
        }
    }

    private fun checkPermission() {
        requireActivity().let {
            when {
                ContextCompat.checkSelfPermission(it,
                    Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED -> {
                    getLocation()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
                -> {
                    showRationaleDialog()
                }
                else -> {
                    requestPermission()
                }
            }
        }

    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )

    }

    private fun showRationaleDialog() {
        requireActivity().let {
            AlertDialog.Builder(it)
                .setTitle(getString(R.string.geo_access_dialog_title_text))
                .setMessage(getString(R.string.geo_access_dialog_text))
                .setPositiveButton(getString(R.string.geo_access_dialog_positive_button_text))
                { _, _ ->
                    requestPermission()
                }
                .setNegativeButton(getString(R.string.geo_access_dialog_negative_button_text)) {
                        dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        checkPermissionsResult(requestCode, grantResults)
    }

    private fun checkPermissionsResult(requestCode: Int, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                var grantedPermissions = 0
                if ((grantResults.isNotEmpty())) {
                    for (i in grantResults) {
                        if (i == PackageManager.PERMISSION_GRANTED) {
                            grantedPermissions++
                        }
                    }
                    if (grantResults.size == grantedPermissions) {
                        getLocation()
                    } else {
                        showDialog(
                            getString(R.string.geo_access_denied_warning_title_text),
                            getString(R.string.geo_access_denied_warning_text)
                        )
                    }
                } else {
                    showDialog(
                        getString(R.string.geo_access_denied_warning_title_text),
                        getString(R.string.geo_access_denied_warning_text)
                    )
                }
                return
            }
        }
    }

    private fun showDialog(title: String, message: String) {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(getString(R.string.close_dialog_button_text)) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun getLocation() {
        activity?.let { context ->
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                val locationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as
                            LocationManager
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    val provider =
                        locationManager.getProvider(LocationManager.GPS_PROVIDER)
                    provider?.let {
                        if (SDK_INT >= android.os.Build.VERSION_CODES.R) {
                            locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER,
                            null,
                            requireActivity().mainExecutor,
                            locationCallback)
                        }
                    }
                } else {
                    showDialog(getString(R.string.gps_off_dialog_title_text), getString(R.string.gps_off_dialog_text))
                }
            } else {
                showRationaleDialog()
            }
        }
    }

    private fun activateMyLocation(googleMap: GoogleMap) {
        context?.let {
            val isPermissionGranted =
                ContextCompat.checkSelfPermission(it,
                    Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED
            googleMap.isMyLocationEnabled = isPermissionGranted
            googleMap.uiSettings.isMyLocationButtonEnabled = isPermissionGranted
        }
    }

    private val locationCallback = Consumer<Location> { location ->
        if (null != location) {
            val currentLocation = LatLng(location.latitude, location.longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10f))
        }
    }

    interface Controller {
        fun openMarkerListFragment()
    }

    companion object {
        fun newInstance() = MapsFragment()
    }
}