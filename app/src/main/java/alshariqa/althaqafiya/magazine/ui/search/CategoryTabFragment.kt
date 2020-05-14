package alshariqa.althaqafiya.magazine.ui.search

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.fragment_search.*


class CategoryTabFragment : BaseFragment(), TabLayout.OnTabSelectedListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //   rv.adapter = CategorySubjectAdapter()
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.search)
    }

    /* override fun onError(error: String) {
         showMessage(error)
         hideProgressBar()

     }*/


    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = CategoryTabFragment()
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }


    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {

        when {
            tabL.getTabAt(0)?.isSelected == true -> {
                tab?.setIcon(R.drawable.ic_subjects_selected)
                tabL.getTabAt(1)?.setIcon(R.drawable.ic_articles)
                tabL.getTabAt(2)?.setIcon(R.drawable.ic_magazines)
            }
            tabL.getTabAt(1)?.isSelected == true -> {
                tab?.setIcon(R.drawable.ic_articles_selected)
                tabL.getTabAt(0)?.setIcon(R.drawable.ic_subjects)
                tabL.getTabAt(2)?.setIcon(R.drawable.ic_magazines)
            }
            else -> {
                tab?.setIcon(R.drawable.ic_magazines_selected)
                tabL.getTabAt(0)?.setIcon(R.drawable.ic_subjects)
                tabL.getTabAt(1)?.setIcon(R.drawable.ic_articles)
            }
        }

    }


}