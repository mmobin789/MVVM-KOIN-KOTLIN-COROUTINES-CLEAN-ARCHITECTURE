package alshariqa.althaqafiya.magazine.ui.article.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ArticlesViewModel : BaseViewModel<ArticlesViewModel.View>() {
    var author: Author? = null
    private val mErrorData = MutableLiveData<String>()
    private val mData = MutableLiveData<String?>()
    private val mArticlesData = MutableLiveData<List<Article>>()
    private val mWritersData = MutableLiveData<List<Author>>()
    private val mArticleDetailData = MutableLiveData<Article>()

    init {
        ArticleRepository.setScope(CoroutineScope(Dispatchers.Main + viewModelJob))
    }

    private val mDataObserver: Observer<in String?> =
        Observer {
            getView().onBookmark(it.isNullOrBlank())
        }
    private val mArticlesDataObserver: Observer<in List<Article>> =
        Observer {
            getView().onArticles(it)
        }
    private val mWritersDataObserver: Observer<in List<Author>> =
        Observer {
            getView().onAuthors(it)
        }

    private val mArticleDetailDataObserver: Observer<in Article> =
        Observer {
            getView().onArticleDetail(it)
        }

    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }


    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mArticlesData.observe(lifecycleOwner, mArticlesDataObserver)
        mWritersData.observe(lifecycleOwner, mWritersDataObserver)
        mArticleDetailData.observe(lifecycleOwner, mArticleDetailDataObserver)
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mData.observe(lifecycleOwner, mDataObserver)
    }

    fun loadAuthors() = ArticleRepository.getAuthors(
        {
            mWritersData.postValue(it)
        },
        {
            showError(it)
        })

    fun loadArticlesByAuthorId(authorId: Int) = ArticleRepository.getArticleByAuthorId(
        authorId, {
            mArticlesData.postValue(it)
        },
        {
            showError(it)
        })

    fun loadArticleDetails(articleId: Int) = ArticleRepository.getArticleDetails(
        articleId, {
            mArticleDetailData.postValue(it)
        },
        {
            showError(it)
        })

    fun addArticle(article: Article) = ArticleRepository.addArticle(article, {
        mData.postValue(null)
    }, {
        mData.postValue(it)
    })

    fun deleteArticle(article: Article) = ArticleRepository.deleteArticle(article, {
        mData.postValue(null)
    }, {
        // handle delete fail case if required.
        mData.postValue(it)
    })

    fun getArticle(id: Int, onSuccess: (Article) -> Unit) = ArticleRepository.getArticle(id, {
        onSuccess(it)
    }, {
        //handle get fail if needed.
        //    showError(it)
    })

    fun onAuthorClick(author: Author) = getView().onAuthorClick(author)

    fun onArticleClick(article: Article) = getView().onArticleClick(article)


    private fun showError(message: String) = mErrorData.postValue(message)


    interface View {
        fun onError(error: String)
        fun onArticles(articles: List<Article>) {}
        fun onArticleClick(article: Article) {}
        fun onArticleDetail(article: Article) {}
        fun onAuthorClick(author: Author) {}
        fun onAuthors(writers: List<Author>) {}
        fun onBookmark(bookmarked: Boolean) {}
    }


}