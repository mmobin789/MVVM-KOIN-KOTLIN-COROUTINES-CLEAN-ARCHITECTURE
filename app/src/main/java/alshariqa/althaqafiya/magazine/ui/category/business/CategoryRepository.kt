package alshariqa.althaqafiya.magazine.ui.category.business

import alshariqa.althaqafiya.magazine.api.NetworkClient
import alshariqa.althaqafiya.magazine.api.OnApiResponseCallback
import alshariqa.althaqafiya.magazine.base.BaseRepository
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories

object CategoryRepository : BaseRepository() {
    //    private val mTAG = javaClass.simpleName
    private val categoryService = NetworkClient.categoryService

    fun getCategoriesWithArticles(
        categoryId: Int,
        onSuccess: (List<Categories.Data>) -> Unit,
        onFail: (String) -> Unit
    ) {

        categoryService.getCategoriesWithArticles(categoryId)
            .enqueue(object : OnApiResponseCallback<Categories> {
                override fun onSuccess(response: Categories?) {
                    response?.run {
                        onSuccess(data)
                    }
                }

                override fun onFail(error: String) {
                    onFail(error)
                }
            })
    }


}