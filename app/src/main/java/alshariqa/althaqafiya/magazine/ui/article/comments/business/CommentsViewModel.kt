package alshariqa.althaqafiya.magazine.ui.article.comments.business

import alshariqa.althaqafiya.magazine.base.BaseViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class CommentsViewModel : BaseViewModel<CommentsViewModel.View>() {

    private val mErrorData = MutableLiveData<String>()


    private val mErrorObserver: Observer<in String> = Observer {
        getView().onError(it)
    }


    fun addObservers(lifecycleOwner: LifecycleOwner) {
        mErrorData.observe(lifecycleOwner, mErrorObserver)
    }


    fun writeComment() = getView().writeComment()


    private fun showError(message: String) = mErrorData.postValue(message)


    interface View {
        fun onError(error: String)
        fun writeComment()
    }


}