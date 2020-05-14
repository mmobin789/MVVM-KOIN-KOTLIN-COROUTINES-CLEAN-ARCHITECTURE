package alshariqa.althaqafiya.magazine.ui.navigation.distribution.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import alshariqa.althaqafiya.magazine.ui.navigation.NavigationRepository
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.model.Distribution
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class DistributionViewModel : BaseViewModel<DistributionViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()
    private val mDistributionData = MutableLiveData<List<Distribution.Data>>()
    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }
    private val mDistributionDataObserver: Observer<in List<Distribution.Data>> = Observer {
        getView().onDistribution(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mErrorData.observe(lifecycleOwner, mErrorObserver)
        mDistributionData.observe(lifecycleOwner, mDistributionDataObserver)
    }

    fun loadDistributors() = NavigationRepository.getDistributors({
        mDistributionData.postValue(it)
    }, {
        mErrorData.postValue(it)
    })

    interface View {
        fun onError(error: String)
        fun onDistribution(distribution: List<Distribution.Data>)
    }
}