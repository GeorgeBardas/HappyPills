package com.happypills.ui.stores

import android.content.Context
import android.location.Location
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Response
import com.android.volley.VolleyError
import com.google.gson.GsonBuilder
import com.happypills.objects.Store
import com.happypills.util.googleplaces.VolleyService
import org.json.JSONObject

class StoresViewModel : ViewModel(), Response.Listener<JSONObject>,
    Response.ErrorListener {

    val locationsList: MutableLiveData<List<Store>> = MutableLiveData()
    val locationsError: MutableLiveData<Boolean> = MutableLiveData()

    fun getData(context:Context?, it: Location) {
        context?.let { ctx ->
            VolleyService.getInstance(ctx).getNearStores(it.longitude, it.latitude,this, this)
        }
    }

    override fun onResponse(response: JSONObject?) {
        locationsList.value = GsonBuilder()
            .create()
            .fromJson(response?.getJSONArray("results").toString(), Array<Store>::class.java).toList()
        locationsError.value = true
    }

    override fun onErrorResponse(error: VolleyError?) {
        locationsError.value = false
    }

}