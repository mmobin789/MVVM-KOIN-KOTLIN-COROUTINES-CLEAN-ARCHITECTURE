package alshariqa.althaqafiya.magazine.ui.navigation.editorial

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.navigation.NavigationRepository
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class EditorialViewModel : BaseViewModel<EditorialViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mData = MutableLiveData<String>()

    init {
        NavigationRepository.setScope(CoroutineScope(Dispatchers.Main + viewModelJob))
    }

    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }
    private val mDataObserver: Observer<in String> = Observer {
        getView().onEditorial(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mData.observe(lifecycleOwner, mDataObserver)
    }

    fun getEditorial() = NavigationRepository.getEditorial({
        mData.postValue(it)
    }, {
        mErrorData.postValue(it)
    })

    interface View {
        fun onError(error: String)
        fun onEditorial(html: String)
    }
}