package alshariqa.althaqafiya.magazine.ui.article.business

import alshariqa.althaqafiya.magazine.api.NetworkClient
import alshariqa.althaqafiya.magazine.api.OnApiResponseCallback
import alshariqa.althaqafiya.magazine.base.BaseRepository
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.article.business.model.ArticleDetails
import alshariqa.althaqafiya.magazine.ui.article.business.model.Articles
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ArticleRepository : BaseRepository() {
    private val mTAG = javaClass.simpleName
    private val articleService = NetworkClient.articleService
    private val articlesDAO = appDatabase.getArticlesDAO()

    fun addArticle(article: Article, onSuccess: () -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val id = withContext(Dispatchers.IO)
            {
                articlesDAO.insert(article)
            }

            if (id > -1)
                onSuccess()
            else onFail("$mTAG failed to insert article in room.")
        }
    }

    fun deleteArticle(
        article: Article,
        onSuccess: (() -> Unit)? = null,
        onFail: ((String) -> Unit)? = null
    ) {
        getScope().launch {
            val id = withContext(Dispatchers.IO)
            {
                articlesDAO.delete(article)
            }

            if (id > -1)
                onSuccess?.invoke()
            else onFail?.invoke("$mTAG failed to delete article in room.")
        }
    }

    fun getArticle(id: Int, onSuccess: (Article) -> Unit, onFail: (String) -> Unit) {
        getScope().launch {
            val article = withContext(Dispatchers.IO)
            {
                articlesDAO.getArticle(id)
            }
            article?.also {
                onSuccess(it)
            } ?: also {
                onFail("$mTAG failed to find article in room.")
            }
        }
    }

    fun getArticleByAuthorId(
        authorId: Int,
        onSuccess: (List<Article>) -> Unit,
        onFail: (String) -> Unit
    ) {

        articleService.getArticlesByAuthorId(authorId)
            .enqueue(object : OnApiResponseCallback<Articles> {
                override fun onSuccess(response: Articles?) {
                    response?.data?.run {
                        onSuccess(articles)
                    }
                }

                override fun onFail(error: String) {
                    onFail(error)
                }
            })
    }

    fun getArticleDetails(articleId: Int, onSuccess: (Article) -> Unit, onFail: (String) -> Unit) {
        articleService.getArticleDetails(articleId)
            .enqueue(object : OnApiResponseCallback<ArticleDetails> {
                override fun onSuccess(response: ArticleDetails?) {
                    response?.data?.run {
                        onSuccess(detail)
                    }
                }

                override fun onFail(error: String) {
                    onFail(error)
                }
            })

    }

    fun getAuthors(
        onSuccess: (List<Author>) -> Unit,
        onFail: (String) -> Unit
    ) {

        articleService.getAuthors().enqueue(object : OnApiResponseCallback<Articles> {
            override fun onSuccess(response: Articles?) {
                response?.data?.run {
                    onSuccess(writers)
                }
            }

            override fun onFail(error: String) {
                onFail(error)
            }
        })
    }

}