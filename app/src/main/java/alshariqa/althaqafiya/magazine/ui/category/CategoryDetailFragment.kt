package alshariqa.althaqafiya.magazine.ui.category

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.adapters.CategorySubjectAdapter
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_category_detail.*


class CategoryDetailFragment : BaseFragment(), CategoryViewModel.View {

    private val mCategoryViewModel: CategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mCategoryViewModel.let {
            it.attachView(this)
            it.addObservers(this)

            arguments?.run {
                val categoryId = getInt(Keys.CATEGORY_ID)
                it.loadCategories(categoryId)
            }
        }


    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.subjects)
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }

    override fun onCategories(categories: List<Categories.Data>) {
        rvSubjects.adapter =
            CategorySubjectAdapter(categories[0].articles, mCategoryViewModel, true)
        hideProgressBar()
    }

    override fun onArticleClicked(article: Article) {
        mainViewModel.loadArticleDetail(article)
    }

    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = CategoryDetailFragment()
    }
}