package alshariqa.althaqafiya.magazine.ui.category.business.model

import alshariqa.althaqafiya.magazine.base.BaseResponse
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import com.google.gson.annotations.SerializedName

data class Categories(@SerializedName("Data") val data: List<Data>) : BaseResponse(
) {
    inner class Data(
        @SerializedName("CategoryId") val categoryId: Int,
        @SerializedName("CategoryName") val categoryName: String,
        @SerializedName("ImageUrl") val imageUrl: String?,
        @SerializedName("ArticleList") val articles: List<Article>

    )
}