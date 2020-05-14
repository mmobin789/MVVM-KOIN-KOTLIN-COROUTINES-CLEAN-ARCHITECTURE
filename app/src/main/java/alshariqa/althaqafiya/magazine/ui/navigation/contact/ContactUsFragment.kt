package alshariqa.althaqafiya.magazine.ui.navigation.contact

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.base.logD
import alshariqa.althaqafiya.magazine.ui.navigation.contact.business.model.ContactInfo
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
import kotlinx.android.synthetic.main.fragment_contact_us.*


class ContactUsFragment : BaseFragment(), OnMapReadyCallback, ContactUsViewModel.View {

    private val mContactUsViewModel: ContactUsViewModel by viewModels()
    private lateinit var mGoogleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_contact_us, container, false)
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

        mContactUsViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadContactInfo()

        }


    }

    override fun onMapReady(p0: GoogleMap) {
        mGoogleMap = p0
        mGoogleMap.run {
            uiSettings.isMyLocationButtonEnabled = true
            uiSettings.setAllGesturesEnabled(true)
            mContactUsViewModel.loadContactInfo()

        }
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun onContactInfo(contactInfo: ContactInfo.Data) {
        hideProgressBar()
        updateMap(contactInfo)
        vContent.loadDataWithBaseURL(
            null,
            getHtmlData(contactInfo.content),
            "text/html", Charsets.UTF_8.displayName(), null
        )

    }

    private fun getHtmlData(
        data: String
    ): String {

        logD(javaClass.simpleName, data)

        val head =
            "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/noto_kufi_arabic_regular.ttf\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>"

        val body = data.substring(6, data.length - 7)
        val bodyClosure = "</body></html>"
        return head + body + bodyClosure
    }

    private fun updateMap(contactInfo: ContactInfo.Data) {
        val latLng = LatLng(contactInfo.latitude, contactInfo.longitude)
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11f))
        mGoogleMap.addMarker(
            MarkerOptions()
                .position(LatLng(contactInfo.latitude, contactInfo.longitude))
        )
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
        fun newInstance() = ContactUsFragment()
    }


}