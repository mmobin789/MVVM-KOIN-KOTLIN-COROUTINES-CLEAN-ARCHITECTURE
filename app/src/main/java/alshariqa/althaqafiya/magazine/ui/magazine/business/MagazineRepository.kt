package alshariqa.althaqafiya.magazine.ui.magazine.business

import alshariqa.althaqafiya.magazine.api.NetworkClient
import alshariqa.althaqafiya.magazine.api.OnApiResponseCallback
import alshariqa.althaqafiya.magazine.base.BaseRepository
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchive
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.Magazines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object MagazineRepository : BaseRepository() {
    private val mTAG = javaClass.simpleName
    private val magazinesDAO = appDatabase.getMagazinesDAO()
    private val magazineService = NetworkClient.magazineService


    fun addMagazine(magazine: Magazine, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val id = withContext(Dispatchers.IO)
            {
                magazinesDAO.insert(magazine)
            }

            if (id > -1)
                onSuccess()
            else onFail("$mTAG failed to insert article in room.")
        }
    }

    fun deleteMagazine(
        magazine: Magazine,
        onSuccess: (() -> Unit)? = null,
        onFail: ((String) -> Unit)? = null
    ) {
        getScope().launch {
            val id = withContext(Dispatchers.IO)
            {
                magazinesDAO.delete(magazine)
            }

            if (id > -1)
                onSuccess?.invoke()
            else onFail?.invoke("$mTAG failed to delete article in room.")
        }
    }

    fun getMagazine(id: Int, onSuccess: (Magazine) -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val magazine = withContext(Dispatchers.IO)
            {
                magazinesDAO.getMagazine(id)
            }
            magazine?.also {
                onSuccess(it)
            } ?: also {
                onFail("$mTAG failed to find article in room.")
            }
        }
    }

    fun getMagazines(
        page: Int,
        year: Int,
        month: Int,
        onSuccess: (ArrayList<MagazineArchive>) -> Unit,
        onFail: (String) -> Unit
    ) {

        magazineService.getMagazines(page, year, month)
            .enqueue(object : OnApiResponseCallback<Magazines> {
                override fun onSuccess(response: Magazines?) {
                    response?.data?.run {
                        onSuccess(magazineArchives)
                    }
                }

                override fun onFail(error: String) {
                    onFail(error)
                }

            })
    }


}