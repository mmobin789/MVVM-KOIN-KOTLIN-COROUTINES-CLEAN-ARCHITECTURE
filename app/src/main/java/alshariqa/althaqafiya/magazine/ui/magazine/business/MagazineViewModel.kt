package alshariqa.althaqafiya.magazine.ui.magazine.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchive
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MagazineViewModel : BaseViewModel<MagazineViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mMagazineData = MutableLiveData<ArrayList<MagazineArchive>>()
    private val mMagazineUpdateData = MutableLiveData<String?>()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var pageNo = 1
    private var year = 0
    private var month = 0
    private var clear = false
    private var loading = false
    private var filter1 = ""
    private var filter2 = ""

    init {
        MagazineRepository.setScope(uiScope)
    }

    private val mMagazineDataObserver: Observer<in ArrayList<MagazineArchive>> = Observer {
        //  if (it.isNotEmpty()) {
        // val totalPageCount = it[0]?.totalPageCount ?: 1
        loading = false
        getView().onMagazines(it, pageNo > 1, clear, filter1, filter2)
        pageNo++

    }
    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }
    private val mMagazineUpdateDataObserver: Observer<in String?> = Observer {
        getView().onBookmark(it == null)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mMagazineData.observe(lifecycleOwner, mMagazineDataObserver)
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mMagazineUpdateData.observe(lifecycleOwner, mMagazineUpdateDataObserver)
    }


    fun loadMagazines(
        pageNo: Int = this.pageNo,
        month: Int = this.month,
        year: Int = this.year,
        clear: Boolean = false,
        filter1: String = this.filter1,
        filter2: String = this.filter2
    ) {
        this.clear = clear
        this.filter1 = filter1
        this.filter2 = filter2

        if (!loading) {
            loading = true
            this.pageNo = pageNo
            this.month = month
            this.year = year
            MagazineRepository.getMagazines(pageNo, year, month, {
                mMagazineData.postValue(it)

            }, {
                loading = false
                showError(it)
            })
        }
    }

    fun getMagazine(id: Int, onSuccess: (Magazine) -> Unit, onFail: (String) -> Unit) =
        MagazineRepository.getMagazine(id, onSuccess, onFail)

    fun addToFavorite(magazine: Magazine) {
        magazine.isLiked = true
        MagazineRepository.addMagazine(magazine, {
            mMagazineUpdateData.postValue(null)
        }, {
            mMagazineUpdateData.postValue(it)
        })
    }

    fun onMagazineClick(magazine: Magazine) = getView().onMagazineClick(magazine)

    fun onMagazineOptionsClick(magazine: Magazine) = getView().onMagazineOptionsClick(magazine)

    private fun showError(message: String) = mErrorData.postValue(message)


    interface View {
        fun onError(error: String)
        fun onMagazines(
            magazines: ArrayList<MagazineArchive>,
            update: Boolean,
            clear: Boolean,
            filter1: String,
            filter2: String
        )

        fun onMagazineClick(magazine: Magazine)
        fun onMagazineOptionsClick(magazine: Magazine)
        fun onBookmark(bookmark: Boolean)
    }
}