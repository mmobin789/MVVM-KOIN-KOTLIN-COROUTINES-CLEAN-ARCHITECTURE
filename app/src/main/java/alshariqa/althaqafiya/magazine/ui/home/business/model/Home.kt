package alshariqa.althaqafiya.magazine.ui.home.business.model

import alshariqa.althaqafiya.magazine.base.BaseResponse
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import com.google.gson.annotations.SerializedName

data class Home(@SerializedName("Data") val data: Data) : BaseResponse() {

    inner class Data(
        @SerializedName("lstFeaturedArticles")
        val articles: List<Article>,
        @SerializedName("lstSubjectCategory")
        val categories: List<Category>,
        @SerializedName("lstMagazine")
        val magazines: List<Magazine>,
        @SerializedName("lstMagazineWriters")
        val authors: List<Author>,
        @SerializedName("lstTop4Articles")
        val top4Articles: List<Article>
    )
}