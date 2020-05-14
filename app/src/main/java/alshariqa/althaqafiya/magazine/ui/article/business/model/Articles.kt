package alshariqa.althaqafiya.magazine.ui.article.business.model

import alshariqa.althaqafiya.magazine.base.BaseResponse
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import com.google.gson.annotations.SerializedName

data class Articles(@SerializedName("Data") val data: Data) : BaseResponse() {

    inner class Data(
        @SerializedName("lstArticlesByAuthor") val articles: List<Article>,
        @SerializedName("lstMagazineWriters") val writers: List<Author>
    )
}