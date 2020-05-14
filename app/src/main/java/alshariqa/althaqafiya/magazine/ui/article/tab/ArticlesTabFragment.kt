package alshariqa.althaqafiya.magazine.ui.article.tab

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.adapters.ArticlesTabAdapter
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.ui.main.Settings
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import kotlinx.android.synthetic.main.fragment_articles_tab.*


class ArticlesTabFragment : BaseFragment(), ArticlesViewModel.View {

    private val articlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_articles_tab, container, false)
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
            it.loadAuthors()

        }

    }

    override fun getActivitySettings(): Settings {
        return Settings.ARTICLES
    }

    override fun onAuthors(writers: List<Author>) {
        rv.adapter = ArticlesTabAdapter(writers, articlesViewModel)
        hideProgressBar()
    }

    override fun onAuthorClick(author: Author) {
        mainViewModel.loadArticlesByAuthor(author)
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
            ArticlesTabFragment()
    }


}