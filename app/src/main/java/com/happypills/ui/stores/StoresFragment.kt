package com.happypills.ui.stores

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.VolleyError
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.gson.GsonBuilder
import com.happypills.MainActivity
import com.happypills.R
import com.happypills.objects.Pill
import com.happypills.objects.Store
import com.happypills.ui.stores.util.StoreRecyclerViewAdapter
import com.happypills.util.googleplaces.VolleyService
import kotlinx.android.synthetic.main.fragment_stores.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class StoresFragment : Fragment(), OnMapReadyCallback {

    private lateinit var storesViewModel: StoresViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var googleMap: GoogleMap? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var storeRecyclerViewAdapter: StoreRecyclerViewAdapter

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val DEFAULT_ZOOM = 15
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
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setupRecyclerView()
    }

    private fun updateMyLocation() {
        googleMap?.apply {
            isMyLocationEnabled = true
            uiSettings?.apply {
                isZoomGesturesEnabled = true
                isZoomControlsEnabled = true
            }
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            googleMap?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(it.latitude, it.longitude),
                    DEFAULT_ZOOM.toFloat()
                )
            )
            GlobalScope.launch(Dispatchers.IO) { storesViewModel.getData(context, it) }
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let { googleMap = it }
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
        updateMap()
    }

    @AfterPermissionGranted(LOCATION_PERMISSION_REQUEST_CODE)
    private fun updateMap() {
        context?.let {
            if (EasyPermissions.hasPermissions(
                    it,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                updateMyLocation()
            } else
                EasyPermissions.requestPermissions(
                    this,
                    "",
                    LOCATION_PERMISSION_REQUEST_CODE,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setupRecyclerView(){
        recyclerView = store_list
        val listStores = mutableListOf<Store>()
        storesViewModel.locationsList.observeForever {
            listStores.clear()
            listStores.addAll(it)
        }
        storeRecyclerViewAdapter = StoreRecyclerViewAdapter(listStores)
        recyclerView.adapter = storeRecyclerViewAdapter

        storesViewModel.locationsList.observeForever {
            storeRecyclerViewAdapter.notifyDataSetChanged()
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}