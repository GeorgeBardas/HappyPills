package com.happypills.util.googleplaces

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.happypills.R
import org.json.JSONObject

class VolleyService constructor(val context: Context) {
    companion object {

        val BASE_PATH_GOOGLE_PLACES = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
        val SEARCH_RADIUS = 7500
        val SEARCH_TYPE = "drugstore"

        @Volatile
        private var INSTANCE: VolleyService? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyService(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    fun getNearStores(
        longitude: Double,
        latitude: Double,
        successListener: Response.Listener<JSONObject>,
        errorListener: Response.ErrorListener
    ) {
        addToRequestQueue(
            JsonObjectRequest(
                Request.Method.GET,
                "${BASE_PATH_GOOGLE_PLACES}location=$latitude,$longitude&radius=$SEARCH_RADIUS&type=$SEARCH_TYPE&key=${context.getString(
                    R.string.google_maps_key
                )}",
                null,
                successListener,
                errorListener
            )
        )
    }
}

