package alshariqa.althaqafiya.magazine.ui.navigation.distribution

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.DistributionViewModel
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.MapInfoWindowAdapter
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.model.Distribution
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_distribution.*


class DistributionFragment : BaseFragment(), OnMapReadyCallback, DistributionViewModel.View {

    private val mDistributionViewModel: DistributionViewModel by viewModels()
    private lateinit var mGoogleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_distribution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ivBack.setOnClickListener {
            mainActivity.onBackClicked(it)
        }
        ivSideBar.setOnClickListener {
            mainActivity.onDrawerClicked(it)
        }

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)

        mDistributionViewModel.let {
            it.attachView(this)
            it.addObservers(this)
        }


    }

    override fun onMapReady(p0: GoogleMap) {
        mGoogleMap = p0
        mGoogleMap.run {
            uiSettings.isMyLocationButtonEnabled = true
            uiSettings.setAllGesturesEnabled(true)

        }
        mDistributionViewModel.loadDistributors()
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun onDistribution(distribution: List<Distribution.Data>) {
        updateMap(distribution)
        hideProgressBar()
    }

    private fun updateMap(distribution: List<Distribution.Data>) {
        val latLng = LatLng(distribution[0].latitude, distribution[0].longitude)
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5.5f))
        distribution.forEach {
            mGoogleMap.addMarker(
                MarkerOptions().snippet(it.name)
                    .position(LatLng(it.latitude, it.longitude))
            )
            mGoogleMap.setInfoWindowAdapter(MapInfoWindowAdapter(mainActivity))

            /*    mGoogleMap.setOnMarkerClickListener { marker ->
                    if (marker.isInfoWindowShown)
                        marker.hideInfoWindow()
                    else
                        marker.showInfoWindow()
                    true
                }*/
        }
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }

    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = DistributionFragment()
    }


}