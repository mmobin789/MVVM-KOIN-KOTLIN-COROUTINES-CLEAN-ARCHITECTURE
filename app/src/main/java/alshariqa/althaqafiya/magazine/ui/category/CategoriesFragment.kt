package alshariqa.althaqafiya.magazine.ui.category

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.adapters.CategoriesTabAdapter
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import alshariqa.althaqafiya.magazine.ui.main.Settings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : BaseFragment(), CategoryViewModel.View {

    private val mCategoryViewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mCategoryViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadCategories()
        }
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.subjects)
    }

    override fun getActivitySettings(): Settings {
        return Settings.CATEGORIES
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }

    override fun onCategories(categories: List<Categories.Data>) {
        rv.adapter = CategoriesTabAdapter(categories, mCategoryViewModel)
        hideProgressBar()
    }

    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }


    override fun onArticleClicked(article: Article) {
        mainViewModel.loadArticleDetail(article)
    }

    override fun onCategoryClick(categoryId: Int) {
        mainViewModel.loadCategoryDetails(categoryId)
    }


    companion object {
        fun newInstance() = CategoriesFragment()
    }
}