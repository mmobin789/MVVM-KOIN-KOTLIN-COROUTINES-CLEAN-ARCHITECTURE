package alshariqa.althaqafiya.magazine.ui.main

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainViewModel : BaseViewModel<MainViewModel.View>() {

    private val mCentralMessageData = MutableLiveData<String>()
    //  private val countDownTimer = SimpleCountDownTimer(0, 2, this)


    private fun splashScreen() {
        //  if (!splashComplete) {
        getView().onSplashScreen()
        //    splashComplete = true
        //}
    }

    private val mCentralMessageDataObserver: Observer<in String> = Observer {
        getView().showMessage(it)
    }

    /*  private val mNavigationDataObserver: Observer<in Int?> = Observer {
          splashComplete = it == 1
          getView().onSplashScreen()
      }*/


    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mCentralMessageData.observe(lifecycleOwner, mCentralMessageDataObserver)
        splashScreen()
        //  mNavigationData.observe(lifecycleOwner, mNavigationDataObserver)
    }

    // fun loadArticles() = getView().loadArticles()

    fun loadArticlesByAuthor(author: Author) = getView().loadArticlesByAuthor(author)

    fun loadArticles(authorId: Int) = getView().loadArticles(authorId)

    fun loadArticleDetail(article: Article) = getView().loadArticleDetails(article)

    fun loadAuthorArticleDetails(writer: Author, article: Article) =
        getView().loadAuthorArticleDetails(writer, article)

    fun loadCategories() = getView().loadCategories()

    fun loadMagazines() = getView().loadMagazines()

    fun loadMagazine(magazine: Magazine) = getView().loadMagazine(magazine)

    fun loadComments() = getView().loadComments()


    fun showBottomBar(show: Boolean) = getView().showBottomBar(show)

    fun showToolBar(title: String?) = getView().showToolBar(title)


    fun showMessage(message: String) = mCentralMessageData.postValue(message)

    fun loadCategoryDetails(categoryId: Int) = getView().loadCategoryDetails(categoryId)

    fun writeComment() = getView().writeComment()

    fun loadSearch() = getView().loadSearch()

    fun loadAboutUs() = getView().loadAboutUs()

    fun loadEditorial() = getView().loadEditorial()

    fun loadDistribution() = getView().loadDistribution()

    fun loadSubscription() = getView().loadSubscription()

    fun loadContactUs() = getView().loadContactUs()

    fun loadSettings() = getView().loadSettings()

    fun loadFavourite() = getView().loadFavourite()


    interface View {
        fun showMessage(message: String)
        fun loadHomePage()
        fun loadSearch()
        fun loadAboutUs()
        fun loadEditorial()
        fun loadDistribution()
        fun loadSubscription()
        fun loadContactUs()
        fun loadSettings()
        fun loadFavourite()
        fun loadCategories()
        fun loadCategoryDetails(categoryId: Int)
        fun loadArticles()
        fun loadArticles(authorId: Int)
        fun loadArticlesByAuthor(author: Author)
        fun loadArticleDetails(article: Article)
        fun loadAuthorArticleDetails(writer: Author, article: Article)
        fun loadMagazines()
        fun loadMagazine(magazine: Magazine)
        fun loadComments()
        fun writeComment()
        fun showBottomBar(show: Boolean)
        fun showToolBar(title: String?)
        fun onSplashScreen()
    }

}