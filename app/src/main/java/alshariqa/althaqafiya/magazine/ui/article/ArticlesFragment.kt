package alshariqa.althaqafiya.magazine.ui.article

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.adapters.ArticlesAdapter
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_articles.*


class ArticlesFragment : BaseFragment(), ArticlesViewModel.View {

    private val articlesViewModel: ArticlesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        articlesViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            arguments?.run {
                val authorId = getInt(Keys.AUTHOR_ID)
                it.loadArticlesByAuthorId(authorId)
            }
        }

    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun onArticles(articles: List<Article>) {
        rv.adapter = ArticlesAdapter(articles, articlesViewModel)
        hideProgressBar()
    }

    override fun onArticleClick(article: Article) {
        mainViewModel.loadArticleDetail(article)
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }


    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    companion object {
        fun newInstance() = ArticlesFragment()
    }


}