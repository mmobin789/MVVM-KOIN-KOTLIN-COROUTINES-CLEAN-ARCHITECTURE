package alshariqa.althaqafiya.magazine.ui.navigation.favourite.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.ArticleRepository
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.business.MagazineRepository
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class FavouriteViewModel : BaseViewModel<FavouriteViewModel.View>() {

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val mMagazineData = MutableLiveData<Magazine>()

    private val mMagazineDataObserver: Observer<in Magazine> = Observer {
        getView().onMagazineDeleted(it)
    }

    init {
        ArticleRepository.setScope(uiScope)
        FavouriteRepository.setScope(uiScope)
        MagazineRepository.setScope(uiScope)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mMagazineData.observe(lifecycleOwner, mMagazineDataObserver)
    }

    private val mArticlesDataObserver: Observer<in List<Article>> =
        Observer {
            getView().onArticles(it)
        }

    private val mMagazinesDataObserver: Observer<in List<Magazine>> =
        Observer {
            getView().onMagazines(it)
        }

    fun getFavoriteArticles(lifecycleOwner: LifecycleOwner) {
        val articlesData = FavouriteRepository.getLikedArticles()
        articlesData.observe(lifecycleOwner, mArticlesDataObserver)

    }

    fun deleteArticle(article: Article) = ArticleRepository.deleteArticle(article)

    fun getFavoriteMagazines(lifecycleOwner: LifecycleOwner) {
        val magazinesData = FavouriteRepository.getFavoriteMagazines()
        magazinesData.observe(lifecycleOwner, mMagazinesDataObserver)

    }

    /*   fun getMagazine(id: Int, onSuccess: (Magazine) -> Unit, onFail: (String) -> Unit) =
           MagazineRepository.getMagazine(id, onSuccess, onFail)

       fun saveMagazine(magazine: Magazine) =
           MagazineRepository.addMagazine(magazine, {
               mMagazineData.postValue(null)
           }, {
               mMagazineData.postValue(it)
           })*/

    fun deleteMagazine(magazine: Magazine) = MagazineRepository.deleteMagazine(magazine, {
        mMagazineData.postValue(magazine)
    }, {
        // if delete fails
    })

    override fun onCleared() {
        viewModelJob.cancel()
    }

    interface View {
        fun onArticles(articles: List<Article>) {}
        fun onMagazines(magazines: List<Magazine>) {}
        fun onMagazineDeleted(magazine: Magazine) {}
    }
}