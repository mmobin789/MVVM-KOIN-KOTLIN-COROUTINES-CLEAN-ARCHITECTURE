package alshariqa.althaqafiya.magazine.ui.navigation.distribution.business

import alshariqa.althaqafiya.magazine.R
import android.content.Context
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.layout_map_window.view.*

class MapInfoWindowAdapter(private val context: Context) : GoogleMap.InfoWindowAdapter {
    private val view: View by lazy {
        View.inflate(context, R.layout.layout_map_window, null)
    }

    override fun getInfoContents(p0: Marker): View? {
        view.tvMap.text = p0.snippet
        return view
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }
}