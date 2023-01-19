package profitsw2000.diffapps.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.Marker

class MapViewModel : ViewModel() {

    private val markerLiveData = MutableLiveData<ArrayList<Marker>>()

    init {
        markerLiveData.value = arrayListOf()
    }

    fun addMarker(marker: Marker) {
        markerLiveData.value?.add(marker)
    }

    fun getMarkerList() : LiveData<ArrayList<Marker>> {
        return markerLiveData
    }

    fun deleteMarker(position: Int) {
        val markerList = getMarkerList().value
        markerList?.let {
            it.removeAt(position)
        }
        markerLiveData.value = markerList
    }

    fun editMarkerTitle(position: Int, title: String) {
        val markerList = getMarkerList().value
        markerList?.let {
            it[position].title = title
        }
        markerLiveData.value = markerList
    }
}