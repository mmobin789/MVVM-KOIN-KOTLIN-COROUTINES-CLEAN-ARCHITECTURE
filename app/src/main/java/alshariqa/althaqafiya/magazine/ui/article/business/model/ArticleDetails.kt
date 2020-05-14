package alshariqa.althaqafiya.magazine.ui.article.business.model

import com.google.gson.annotations.SerializedName

data class ArticleDetails(@SerializedName("Data") val data: Data) {

    inner class Data(@SerializedName("lstArticleDetail") val detail: Article)
}