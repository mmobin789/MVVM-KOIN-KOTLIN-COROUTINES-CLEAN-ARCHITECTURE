package alshariqa.althaqafiya.magazine.ui.navigation

import alshariqa.althaqafiya.magazine.api.NetworkClient
import alshariqa.althaqafiya.magazine.api.OnApiResponseCallback
import alshariqa.althaqafiya.magazine.base.BaseRepository
import alshariqa.althaqafiya.magazine.ui.navigation.contact.business.model.ContactInfo
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.model.Distribution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object NavigationRepository : BaseRepository() {

    private val navigationService = NetworkClient.navigationService

    fun getDistributors(
        onSuccess: (List<Distribution.Data>) -> Unit,
        onFail: (String) -> Unit
    ) {
        navigationService.getDistributors()
            .enqueue(object : OnApiResponseCallback<Distribution> {
                override fun onSuccess(response: Distribution?) {
                    response?.run {
                        onSuccess(data)
                    }
                }

                override fun onFail(error: String) {
                    onFail(error)
                }
            })
    }

    fun getContactUsInfo(onSuccess: (ContactInfo.Data) -> Unit, onFail: (String) -> Unit) {
        navigationService.getContactUsPage().enqueue(object : OnApiResponseCallback<ContactInfo> {
            override fun onSuccess(response: ContactInfo?) {
                response?.run {
                    onSuccess(data)
                }
            }

            override fun onFail(error: String) {
                onFail(error)
            }
        })
    }

    fun getAboutUs(onSuccess: (String) -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val aboutUs = withContext(Dispatchers.IO) {
                navigationService.getAboutUs()

            }
            onSuccess(aboutUs.data.content)

        }
    }

    fun getEditorial(onSuccess: (String) -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val editorial = withContext(Dispatchers.IO) {
                navigationService.getEditorial()

            }
            onSuccess(editorial.data.content)

        }
    }

}