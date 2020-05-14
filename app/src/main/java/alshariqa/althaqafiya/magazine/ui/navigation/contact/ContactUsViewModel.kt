package alshariqa.althaqafiya.magazine.ui.navigation.contact

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.navigation.NavigationRepository
import alshariqa.althaqafiya.magazine.ui.navigation.contact.business.model.ContactInfo
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ContactUsViewModel : BaseViewModel<ContactUsViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mContactInfoData = MutableLiveData<ContactInfo.Data>()
    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }
    private val mContactInfoDataObserver: Observer<in ContactInfo.Data> = Observer {
        getView().onContactInfo(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mContactInfoData.observe(lifecycleOwner, mContactInfoDataObserver)
    }

    fun loadContactInfo() = NavigationRepository.getContactUsInfo({
        mContactInfoData.postValue(it)
    }, {
        mErrorData.postValue(it)
    })

    interface View {
        fun onError(error: String)
        fun onContactInfo(contactInfo: ContactInfo.Data)
    }
}