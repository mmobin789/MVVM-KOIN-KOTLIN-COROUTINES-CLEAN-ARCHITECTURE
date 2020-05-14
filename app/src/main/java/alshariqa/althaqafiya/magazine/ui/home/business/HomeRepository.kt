package alshariqa.althaqafiya.magazine.ui.home.business

import alshariqa.althaqafiya.magazine.api.NetworkClient
import alshariqa.althaqafiya.magazine.api.OnApiResponseCallback
import alshariqa.althaqafiya.magazine.base.BaseRepository
import alshariqa.althaqafiya.magazine.ui.home.business.model.Home

object HomeRepository : BaseRepository() {
    // private val mTAG = javaClass.simpleName
    private val homeService = NetworkClient.homeService

    fun getHomePage(onSuccess: (Home) -> Unit, onFail: (String) -> Unit) {

        homeService.getHomePage().enqueue(object : OnApiResponseCallback<Home> {
            override fun onSuccess(response: Home?) {
                response?.run(onSuccess)
            }

            override fun onFail(error: String) {
                onFail(error)
            }

        })
    }


}