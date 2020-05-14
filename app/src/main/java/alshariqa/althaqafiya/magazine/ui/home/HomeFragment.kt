package alshariqa.althaqafiya.magazine.ui.home

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.adapters.HomeAdapter
import alshariqa.althaqafiya.magazine.ui.home.business.HomeViewModel
import alshariqa.althaqafiya.magazine.ui.home.business.model.Home
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.main.Settings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), HomeViewModel.View {

    private val mHomeViewModel: HomeViewModel by viewModels()
    private var mHomeAdapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mHomeViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadHomePage()
        }


    }

    override fun getActivitySettings(): Settings {
        return Settings.HOME
    }

    /* override fun getToolBarTitle(): String? {
         return getString(R.string.home)
     }*/

    override fun onHomePageReady(home: Home) {
        mHomeAdapter = HomeAdapter(home.data, mainActivity, lifecycle, mHomeViewModel)
        rv.adapter = mHomeAdapter
        hideProgressBar()
    }

    override fun onCategoryClicked(categoryId: Int) {
        mainViewModel.loadCategoryDetails(categoryId)
    }

    override fun onAuthorClicked(authorId: Int) {
        showMessage(R.string.loading)
        mHomeViewModel.loadArticlesByAuthorId(authorId)

    }

    override fun onArticlesByAuthor(articles: List<Article>) {
        mHomeAdapter?.updateArticlesByAuthor(articles)
    }

    override fun onMoreArticlesByAuthor(authorId: Int) {
        mainViewModel.loadArticles(authorId)
    }

    override fun onMagazineClicked(magazine: Magazine) {
        mainViewModel.loadMagazine(magazine)
    }

    override fun onArticleClicked(article: Article) {
        mainViewModel.loadArticleDetail(article)
    }

    override fun onMoreCategoriesClicked() {
        mainViewModel.loadCategories()
    }

    override fun onMoreMagazinesClicked() {
        mainViewModel.loadMagazines()
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
        fun newInstance() = HomeFragment()
    }
}