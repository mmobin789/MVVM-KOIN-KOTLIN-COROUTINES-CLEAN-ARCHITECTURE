package alshariqa.althaqafiya.magazine.ui.magazine

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.adapters.MagazineArchiveAdapter
import alshariqa.althaqafiya.magazine.ui.magazine.business.MagazineViewModel
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchive
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchiveHeader
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineYearly
import alshariqa.althaqafiya.magazine.ui.main.Settings
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_category_detail.progress
import kotlinx.android.synthetic.main.fragment_magazine.*
import java.util.*
import kotlin.collections.ArrayList


class MagazineFragment : BaseFragment(), MagazineViewModel.View,
    MagazineArchiveAdapter.OnFilterClickListener {

    private val mMagazineViewModel: MagazineViewModel by viewModels()

    private lateinit var magazineArchiveAdapter: MagazineArchiveAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_magazine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        mMagazineViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadMagazines()

        }


    }

    override fun getActivitySettings(): Settings {
        return Settings.MAGAZINES
    }

    private fun showMonthsMenu(v: EditText) {
        val months = arrayOf(
            "كل الشهور",
            "يناير",
            "فبراير",
            "مارس",
            "إبريل",
            "مايو",
            "يونيو",
            "يوليو",
            "أغسطس",
            "سبتمبر",
            "أكتوبر",
            "نوفمبر",
            "ديسمبر"
        )

        val popupMenu = PopupMenu(v.context, v)
        popupMenu.menu.run {
            months.forEachIndexed { index, s ->
                add(index, index, Menu.NONE, s)
            }
        }
        popupMenu.setOnMenuItemClickListener { item ->
            val month = item.itemId
            v.setText(item.title)
            mMagazineViewModel.loadMagazines(
                pageNo = 1,
                month = month,
                clear = true,
                filter2 = item.title.toString()
            )
            true
        }
        popupMenu.show()
    }

    private fun showYearsMenu(v: EditText) {
        val popupMenu = PopupMenu(v.context, v)
        popupMenu.menu.run {
            add(0, 0, Menu.NONE, "كل السنوات")
            for (year in Calendar.getInstance().get(Calendar.YEAR) downTo 2014)
                add(year, year, Menu.NONE, year.toString())

        }
        popupMenu.setOnMenuItemClickListener { item ->
            val year = item.itemId
            v.setText(item.title)
            mMagazineViewModel.loadMagazines(
                pageNo = 1,
                year = year,
                clear = true,
                filter1 = item.title.toString()
            )
            true
        }

        popupMenu.show()
    }


    override fun onMonthClick(v: View) {
        showMonthsMenu(v as EditText)
    }

    override fun onYearClick(v: View) {
        showYearsMenu(v as EditText)
    }


    override fun getToolBarTitle(): String? {
        return getString(R.string.archives)
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }

    override fun onMagazines(
        magazines: ArrayList<MagazineArchive>,
        update: Boolean,
        clear: Boolean,
        filter1: String,
        filter2: String
    ) {
        when {
            update -> {
                magazineArchiveAdapter.update(magazines)
            }
            clear -> {
                magazineArchiveAdapter.update(magazines, true, filter1, filter2)
            }
            else -> {
                hideProgressBar()
                val magazineYearly = ArrayList<MagazineYearly>(magazines)
                magazineYearly.add(0, MagazineArchiveHeader())   // add header
                magazineArchiveAdapter =
                    MagazineArchiveAdapter(mMagazineViewModel, magazineYearly, this)
                rv.adapter = magazineArchiveAdapter
            }
        }
    }

    override fun onMagazineClick(magazine: Magazine) {
        mainViewModel.loadMagazine(magazine)
    }

    override fun onBookmark(bookmark: Boolean) {
        if (bookmark)
            showMessage(getString(R.string.saved))
    }

    override fun onMagazineOptionsClick(magazine: Magazine) {
        PopUpUtils.showMagazineOptionsPopUp(rv) {
            onMagazineClick(magazine)
        }
    }

    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = MagazineFragment()
    }


}