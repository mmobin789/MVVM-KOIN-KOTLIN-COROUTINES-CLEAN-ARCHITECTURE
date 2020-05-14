package alshariqa.althaqafiya.magazine.ui.search

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.main.Settings
import alshariqa.althaqafiya.magazine.ui.search.adapters.TabsAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tabsAdapter = TabsAdapter(this)
        vp.adapter = tabsAdapter

        TabLayoutMediator(tabL, vp) { tab, position ->
            vp.currentItem = position
            tab.text = tabsAdapter.getTabText(position)
            tab.icon = ContextCompat.getDrawable(view.context, tabsAdapter.getTabIcon(position))

        }.attach()

        tabL.getTabAt(0)?.select()

        /*  vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
              override fun onPageSelected(position: Int) {
                  tabL.getTabAt(position)?.select()
              }
          })*/
    }

    override fun getActivitySettings(): Settings {
        return Settings.SEARCH
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


    /*  private fun hideProgressBar(show: Boolean = false) {
          progress.visibility = if (show) View.VISIBLE
          else View.GONE
      }*/

    companion object {
        fun newInstance() = SearchFragment()
    }

    /*  override fun onTabReselected(tab: TabLayout.Tab?) {

      }


      override fun onTabUnselected(tab: TabLayout.Tab?) {
      }

      override fun onTabSelected(tab: TabLayout.Tab?) {
          updateTabs()
      }*/

    /*private fun updateTabs() {
        when {
            tabL.getTabAt(0)?.isSelected == true -> {
                tabL.getTabAt(0)?.setIcon(R.drawable.ic_subjects_selected)
                tabL.getTabAt(1)?.setIcon(R.drawable.ic_articles)
                tabL.getTabAt(2)?.setIcon(R.drawable.ic_magazines)
            }
            tabL.getTabAt(1)?.isSelected == true -> {
                tabL.getTabAt(1)?.setIcon(R.drawable.ic_articles_selected)
                tabL.getTabAt(0)?.setIcon(R.drawable.ic_subjects)
                tabL.getTabAt(2)?.setIcon(R.drawable.ic_magazines)
            }
            else -> {
                tabL.getTabAt(2)?.setIcon(R.drawable.ic_magazines_selected)
                tabL.getTabAt(0)?.setIcon(R.drawable.ic_subjects)
                tabL.getTabAt(1)?.setIcon(R.drawable.ic_articles)
            }
        }
    }*/


}