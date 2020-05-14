package alshariqa.althaqafiya.magazine.ui.article.tab

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.base.logD
import alshariqa.althaqafiya.magazine.ui.article.adapters.ArticlesByAuthorAdapter
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils.showAddToFavouritePopUpWindow
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils.showFontSizePopUp
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils.showRatingPopUp
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_article_details_2.*


class ArticleDetailFragment : BaseFragment(), ArticlesViewModel.View {

    private val mArticlesViewModel: ArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_details_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var article: Article? = null
        mArticlesViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            arguments?.run {
                article = getParcelable<Article>(Keys.ARTICLE_DETAIL)?.apply {
                    tvTitle.text = title
                    tvSubTitle.text = subTitle
                    tvDate.text = publishDate
                    it.loadArticleDetails(id)
                    it.getArticle(id) { articleOffline ->
                        article = articleOffline
                        ivLike.setImageResource(R.drawable.ic_liked)
                    }
                }

                getParcelable<Author>(Keys.AUTHOR_DETAIL)?.run {
                    it.author = this
                    ImageLoader.load(thumb, ivAuthor, R.drawable.ic_ph_author)
                    tvAuthor.text = name
                }
            }


        }

        ivRate.setOnClickListener {
            showRatingPopUp(it)
        }

        ivComment.setOnClickListener {
            mainViewModel.loadComments()
        }

        ivLike.setOnClickListener {
            if (article?.isLiked == true) {
                ivLike.setImageResource(R.drawable.ic_like)
                article?.isLiked = false
                mArticlesViewModel.deleteArticle(article!!)
            } else {
                ivLike.setImageResource(R.drawable.ic_liked)
                article?.isLiked = true
                mArticlesViewModel.addArticle(article!!)
                showAddToFavouritePopUpWindow(it)
            }


        }

        ivFont.setOnClickListener {
            showFontPopUpWindow(it)
        }


    }

    private fun showBottomBar() {
        bb.visibility = View.VISIBLE
    }


    /*override fun onScreenTouched(ev: MotionEvent) {
         if (popUp.isShown && ev.action == MotionEvent.ACTION_DOWN) {
              val rect = Rect()
              popUp.getGlobalVisibleRect(rect)
              if (!rect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                  popUp.visibility = View.GONE
                  root.foreground = ColorDrawable(Color.TRANSPARENT)
              }
          }

    }*/

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun onError(error: String) {
        showMessage(error)
        hideProgressBar()

    }

    override fun onBookmark(bookmarked: Boolean) {
    }

    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    private fun showFontPopUpWindow(v: View) {
        showFontSizePopUp(v,
            {
                root.foreground = ColorDrawable(mainActivity.getColor(R.color.black_overlay))
                ivFont.setImageResource(R.drawable.ic_font_highlighted)
            }) { increase ->
            if (increase) {
                var defaultSize = vContent.settings.defaultFontSize
                defaultSize++
                vContent.settings.defaultFontSize = defaultSize
            } else {
                var defaultSize = vContent.settings.defaultFontSize
                defaultSize--
                vContent.settings.defaultFontSize = defaultSize
            }
        }.setOnDismissListener {
            root.foreground = ColorDrawable(Color.TRANSPARENT)
            ivFont.setImageResource(R.drawable.ic_font)
        }
    }


    override fun onArticleDetail(article: Article) {
        article.detail?.also {
            // vContent.visibility = View.VISIBLE
            val s = getHtmlData(it)
            vContent.loadDataWithBaseURL(
                null,
                s,
                "text/html", Charsets.UTF_8.displayName(), null
            )
            hideProgressBar()
            showBottomBar()
        }

        article.authorId?.also {
            mArticlesViewModel.loadArticlesByAuthorId(it)
        }
    }

    override fun onArticles(articles: List<Article>) {
        rv.visibility = View.VISIBLE
        rv.adapter = ArticlesByAuthorAdapter(articles, mArticlesViewModel)
    }

    private fun getHtmlData(
        data: String
    ): String {

        logD(javaClass.simpleName, data)

        val head =
            "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/font/noto_kufi_arabic_regular.ttf\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>"

        val body = data.substring(6, data.length - 7)
        val bodyClosure = "</body></html>"
        return head + body + bodyClosure
    }

    companion object {
        fun newInstance() = ArticleDetailFragment()
    }
}