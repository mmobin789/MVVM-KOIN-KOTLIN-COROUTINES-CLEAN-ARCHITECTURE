package alshariqa.althaqafiya.magazine.ui.navigation.about

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.navigation.NavigationRepository
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AboutUsViewModel : BaseViewModel<AboutUsViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mAboutUsInfoData = MutableLiveData<String>()

    init {
        NavigationRepository.setScope(CoroutineScope(Dispatchers.Main + viewModelJob))
    }

    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }
    private val mAboutUsInfoDataObserver: Observer<in String> = Observer {
        getView().onAboutUs(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mAboutUsInfoData.observe(lifecycleOwner, mAboutUsInfoDataObserver)
    }

    fun loadAboutUs() = NavigationRepository.getAboutUs({
        mAboutUsInfoData.postValue(it)
    }, {
        mErrorData.postValue(it)
    })

    interface View {
        fun onError(error: String)
        fun onAboutUs(html: String)
    }
}