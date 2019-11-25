package com.happypills.ui.stores

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.android.volley.Response
import com.android.volley.VolleyError
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.gson.GsonBuilder
import com.happypills.MainActivity
import com.happypills.R
import com.happypills.objects.Pill
import com.happypills.objects.Store
import com.happypills.util.googleplaces.VolleyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class StoresFragment : Fragment(), OnMapReadyCallback, Response.Listener<JSONObject>,
    Response.ErrorListener {

    private lateinit var storesViewModel: StoresViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        storesViewModel =
            ViewModelProviders.of(this).get(StoresViewModel::class.java)
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context as MainActivity)
        return inflater.inflate(R.layout.fragment_stores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun updateMyLocation() {
        googleMap?.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener {
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        it.latitude,
                        it.longitude
                    ), 15.toFloat()
                )
            )
            GlobalScope.launch(Dispatchers.IO) { getData(it) }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let { googleMap = it }
        updateMap()
    }

    @AfterPermissionGranted(LOCATION_PERMISSION_REQUEST_CODE)
    private fun updateMap() {
        if (EasyPermissions.hasPermissions(
                context!!,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            updateMyLocation()
        } else
            EasyPermissions.requestPermissions(
                this,
                "",
                1,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResponse(response: JSONObject?) {
        val stores = GsonBuilder()
            .create()
            .fromJson(response?.getJSONArray("results").toString(), Array<Store>::class.java).toList()
    }

    override fun onErrorResponse(error: VolleyError?) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
    }

    fun getData(it: Location) {
        context?.let { ctx ->
            VolleyService.getInstance(ctx).getNearStores(it.longitude, it.latitude,this, this)
        }
    }

    private fun setupRecyclerView(){
    }

}