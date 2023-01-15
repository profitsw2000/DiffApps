package profitsw2000.diffapps.presentation.fragments.map.view

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import profitsw2000.diffapps.R

class MapsFragment : Fragment() {

    private val REQUEST_CODE = 10
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

        checkPermission()
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
                .setTitle("Доступ к геолокации")
                .setMessage("Для определения вашего местоположения необходимо разрешить доступ к геолокации.")
                .setPositiveButton("Предоставить доступ")
                { _, _ ->
                    requestPermission()
                }
                .setNegativeButton("Отклонить") {
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
                            "Доступ к геолокации закрыт",
                            "Разрешите доступ к геолокации, если хотите определять свое местоположение."
                        )
                    }
                } else {
                    showDialog(
                        "Доступ к геолокации закрыт",
                        "Разрешите доступ к геолокации, если хотите определять свое местоположение."
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
                .setNegativeButton("Закрыть") { dialog, _ -> dialog.dismiss() }
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
                        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    }
                } else {
                    showDialog("GPS выключен", "Для определения вашего местоположения включите GPS")
                }
            } else {
                showRationaleDialog()
            }
        }
    }


    companion object {
        fun newInstance() = MapsFragment()
    }
}