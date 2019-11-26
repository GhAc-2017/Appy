package com.ayan.appy.maps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ayan.appy.GuestProfile
import com.ayan.appy.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class Gmap : AppCompatActivity() {

    lateinit var mapFragment : SupportMapFragment
    lateinit var googleMap : GoogleMap

    private val REQUEST_LOCATION_PERMISSION = 1

    lateinit var locationManager: LocationManager
    private var hasGps = false
    private var hasNetwork = false
    private var locationGps: Location? = null
    private var locationNetwork: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmap)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            enableMyLocation()
            //googleMap.isMyLocationEnabled = true

            val height = 100
            val width = 100

            val bm = BitmapFactory.decodeResource(resources, R.drawable.profile)
            val smallMarker = Bitmap.createScaledBitmap(bm, width, height, false)

            val loc = LatLng(22.02, 78.45)
            googleMap.addMarker(MarkerOptions().position(loc).title("Mylocation").snippet("Mark Anderson").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 12f))

            googleMap.setOnInfoWindowClickListener {
                startActivity(Intent(this, GuestProfile::class.java))
            }

            setMapLongClick(googleMap)
            setPoiClick(googleMap)
            
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflator = menuInflater
        inflator.inflate(R.menu.map_options, menu)

        return true
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION) === PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            googleMap.setMyLocationEnabled(true)
        }
        else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        // Check if location permissions are granted and if so enable the
        // location data layer.
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.normal_map -> {
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map -> {
            googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map -> {
            googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map -> {
            googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else -> super.onOptionsItemSelected(item)
    }


    private fun setMapLongClick(map:GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            googleMap.addMarker(MarkerOptions().position(latLng))
        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(MarkerOptions().position(poi.latLng).title(poi.name))
            poiMarker.showInfoWindow()
        }
    }
}
