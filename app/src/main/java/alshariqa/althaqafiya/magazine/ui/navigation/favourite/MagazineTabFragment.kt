package alshariqa.althaqafiya.magazine.ui.navigation.favourite

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.home.adapters.MagazineAdapter
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.business.FavouriteViewModel
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_articles.progress
import kotlinx.android.synthetic.main.fragment_magazine.*
import kotlinx.android.synthetic.main.fragment_search.*


class MagazineTabFragment : BaseFragment(), TabLayout.OnTabSelectedListener,
    FavouriteViewModel.View {

    private val mFavouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_magazine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hideSearchUI()
        mFavouriteViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            hideProgressBar()
            it.getFavoriteMagazines(this)
        }
    }

    override fun onMagazines(magazines: List<Magazine>) {
        rv.adapter = MagazineAdapter(magazines, true, onOptionsClicked = {
            PopUpUtils.showFavoriteOptions(rv, {
                mainViewModel.loadMagazine(it)
            }, {
                mFavouriteViewModel.deleteMagazine(it)
            })
        })
    }

    override fun onMagazineDeleted(magazine: Magazine) {

    }

    private fun hideSearchUI() {
        etSearch.visibility = View.GONE
        vLine.visibility = View.GONE
    }

    override fun isBottomBarShown(): Boolean {
        return false
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
        fun newInstance() = MagazineTabFragment()
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