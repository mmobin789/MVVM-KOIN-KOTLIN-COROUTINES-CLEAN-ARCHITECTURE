package alshariqa.althaqafiya.magazine.ui.article.business

import alshariqa.althaqafiya.magazine.base.BaseDAO
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ArticlesDAO : BaseDAO<Article> {
    @Query("SELECT * FROM article WHERE isLiked")
    fun getLikedArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article WHERE id==:id")
    suspend fun getArticle(id: Int): Article?
}