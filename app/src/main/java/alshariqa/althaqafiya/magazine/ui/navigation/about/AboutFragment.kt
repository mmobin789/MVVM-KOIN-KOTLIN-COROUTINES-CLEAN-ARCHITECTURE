package alshariqa.althaqafiya.magazine.ui.navigation.about

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.base.logD
import alshariqa.althaqafiya.magazine.ui.main.Settings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : BaseFragment(), AboutUsViewModel.View {


    private val mAboutUsViewModel: AboutUsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ivBack.setOnClickListener {
            mainActivity.onBackClicked(it)
        }

        ivSideBar.setOnClickListener {
            mainActivity.onDrawerClicked(it)
        }

        mAboutUsViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadAboutUs()
        }


    }

    override fun getActivitySettings(): Settings {
        return Settings.ABOUT_US
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun onAboutUs(html: String) {
        hideProgressBar()
        vContent.loadDataWithBaseURL(
            null,
            getHtmlData(html),
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

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }


    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = AboutFragment()
    }


}