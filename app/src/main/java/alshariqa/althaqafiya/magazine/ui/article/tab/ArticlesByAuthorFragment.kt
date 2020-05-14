package alshariqa.althaqafiya.magazine.ui.article.tab

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.adapters.ArticlesByAuthorAdapter
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.ui.main.Settings
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_author_articles.*


class ArticlesByAuthorFragment : BaseFragment(), ArticlesViewModel.View {

    private val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_author_articles, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        ivBack.setOnClickListener {
            mainActivity.onBackClicked(it)
        }
        ivSideBar.setOnClickListener {
            mainActivity.onDrawerClicked(it)
        }
        articlesViewModel.let {
            it.attachView(this)
            it.addObservers(this)

            arguments?.getParcelable<Author>(Keys.AUTHOR_DETAIL)?.also { author ->
                it.author = author
                ImageLoader.load(author.thumb, ivAuthor, R.drawable.ic_ph_author)
                val totalArticles = "${author.articleCount} ${getString(R.string.articles)}"
                tvTitle.text = totalArticles
                tvAuthor.text = author.name
                it.loadArticlesByAuthorId(author.id)


            }


        }

    }

    override fun getActivitySettings(): Settings {
        return Settings.ARTICLES
    }

    override fun onArticles(articles: List<Article>) {
        rv.adapter = ArticlesByAuthorAdapter(articles, articlesViewModel)
        hideProgressBar()
    }

    override fun onArticleClick(article: Article) {
        mainViewModel.loadAuthorArticleDetails(articlesViewModel.author!!, article)
    }

    override fun isBottomBarShown(): Boolean {
        return false
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
        fun newInstance() =
            ArticlesByAuthorFragment()
    }


}