package alshariqa.althaqafiya.magazine.ui.navigation.favourite

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.adapters.ArticlesByAuthorAdapter
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.business.FavouriteViewModel
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_articles.*
import kotlinx.android.synthetic.main.fragment_search.*


class ArticleTabFragment : BaseFragment(), TabLayout.OnTabSelectedListener,
    FavouriteViewModel.View {

    private val mFavouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mFavouriteViewModel.let {
            it.attachView(this)
            it.getFavoriteArticles(this)
            hideProgressBar()
        }
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
        fun newInstance() = ArticleTabFragment()
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

    override fun onArticles(articles: List<Article>) {
        rv.adapter = ArticlesByAuthorAdapter(articles) {
            PopUpUtils.showFavoriteOptions(rv, {
                mainViewModel.loadArticleDetail(it)
            }, {
                mFavouriteViewModel.deleteArticle(it)
            })
        }

    }


}