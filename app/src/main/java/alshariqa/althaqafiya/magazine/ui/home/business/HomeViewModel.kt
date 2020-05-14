package alshariqa.althaqafiya.magazine.ui.home.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.ArticleRepository
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.model.Home
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class HomeViewModel : BaseViewModel<HomeViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mHomeData = MutableLiveData<Home>()
    private val mArticlesData = MutableLiveData<List<Article>>()

    private val mArticlesDataObserver: Observer<in List<Article>> = Observer {
        getView().onArticlesByAuthor(it)
    }

    private val mHomePageObserver: Observer<in Home> = Observer {
        getView().onHomePageReady(it)
    }
    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mHomeData.observe(lifecycleOwner, mHomePageObserver)
        mArticlesData.observe(lifecycleOwner, mArticlesDataObserver)
        mErrorData.observe(lifecycleOwner, mErrorObserver)
    }


    fun loadHomePage() = HomeRepository.getHomePage({
        mHomeData.postValue(it)
    }, {
        onError(it)
    })

    fun loadArticlesByAuthorId(authorId: Int) = ArticleRepository.getArticleByAuthorId(
        authorId, {
            mArticlesData.postValue(it)
        },
        {
            onError(it)
        })


    fun onError(message: String) = mErrorData.postValue(message)

    fun onMoreCategoriesClicked() = getView().onMoreCategoriesClicked()

    fun onMoreMagazinesClicked() = getView().onMoreMagazinesClicked()

    fun onMoreArticlesByAuthor(authorId: Int) = getView().onMoreArticlesByAuthor(authorId)

    fun onCategoryClicked(categoryId: Int) = getView().onCategoryClicked(categoryId)

    fun onAuthorClicked(authorId: Int) = getView().onAuthorClicked(authorId)

    fun onMagazineClicked(magazine: Magazine) = getView().onMagazineClicked(magazine)

    fun onArticleClicked(article: Article) = getView().onArticleClicked(article)


    interface View {
        fun onError(error: String)
        fun onHomePageReady(home: Home)
        fun onCategoryClicked(categoryId: Int)
        fun onAuthorClicked(authorId: Int)
        fun onArticlesByAuthor(articles: List<Article>)
        fun onMagazineClicked(magazine: Magazine)
        fun onArticleClicked(article: Article)
        fun onMoreCategoriesClicked()
        fun onMoreMagazinesClicked()
        fun onMoreArticlesByAuthor(authorId: Int)
    }
}