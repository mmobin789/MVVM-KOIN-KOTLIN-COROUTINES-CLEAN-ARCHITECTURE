package alshariqa.althaqafiya.magazine.ui.category.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class CategoryViewModel : BaseViewModel<CategoryViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mCategoryData = MutableLiveData<List<Categories.Data>>()
    private val mCategoriesDataObserver: Observer<in List<Categories.Data>> = Observer {
        getView().onCategories(it)
    }
    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }


    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mCategoryData.observe(lifecycleOwner, mCategoriesDataObserver)
        mErrorData.observe(lifecycleOwner, mErrorObserver)
    }

    fun loadCategories(categoryId: Int = 0) = CategoryRepository.getCategoriesWithArticles(
        categoryId, {
            mCategoryData.postValue(it)
        },
        {
            showError(it)
        })

    fun onCategoryClick(categoryId: Int) = getView().onCategoryClick(categoryId)

    fun onArticleClicked(article: Article) = getView().onArticleClicked(article)


    private fun showError(message: String) = mErrorData.postValue(message)


    interface View {
        fun onError(error: String)
        fun onCategories(categories: List<Categories.Data>)
        fun onArticleClicked(article: Article)
        fun onCategoryClick(categoryId: Int) {
            // category detail doesn't require this. Don't implement.
        }
    }


}