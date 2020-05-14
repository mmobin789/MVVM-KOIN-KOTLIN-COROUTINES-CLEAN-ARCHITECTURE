package alshariqa.althaqafiya.magazine.ui.home.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.HomeViewModel
import alshariqa.althaqafiya.magazine.ui.home.business.model.Home
import alshariqa.althaqafiya.magazine.ui.main.MainActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.adapter_home_article.*
import kotlinx.android.synthetic.main.adapter_home_author.*
import kotlinx.android.synthetic.main.adapter_home_category.*
import kotlinx.android.synthetic.main.adapter_home_header.*
import kotlinx.android.synthetic.main.adapter_home_magazine.*
import kotlinx.android.synthetic.main.adapter_home_subject.*
import kotlinx.android.synthetic.main.youtube_layout.*

class HomeAdapter(
    private val mHomeData: Home.Data,
    private val mainActivity: MainActivity,
    private val lifecycle: Lifecycle,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val header = 0
    private val category = 1
    private val topic = 2
    private val magazine = 3
    private val author = 4
    private val article = 5
    private val articleAdapter =
        ArticleAdapter(mHomeData.top4Articles.toMutableList(), homeViewModel)


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.run {
            layoutManager = LinearLayoutManager(recyclerView.context)
            setHasFixedSize(true)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            /* header -> HeaderViewHolder(
                 LayoutInflater.from(parent.context)
                     .inflate(R.layout.adapter_home_header, parent, false)
             )*/
            category -> CategoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_category, parent, false)
            )
            topic -> SubjectViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_subject, parent, false)
            )
            magazine -> MagazineViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_magazine, parent, false)
            )
            author -> AuthorViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_author, parent, false)
            )
            article -> ArticleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_article, parent, false)
            )
            else -> HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_home_header, parent, false)
            )

        }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> header
            1 -> category
            2 -> topic
            3 -> magazine
            4 -> author
            5 -> article
            else -> 6
        }
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        when (getItemViewType(position)) {
            header -> (holder as HeaderViewHolder).bind(position)
            category -> (holder as CategoryViewHolder).bind(position)
            topic -> (holder as SubjectViewHolder).bind(position)
            magazine -> (holder as MagazineViewHolder).bind(position)
            article -> (holder as ArticleViewHolder).bind(position)
            else -> holder.bind(position)
        }
    }


    abstract inner class HomeViewHolder(v: View) : BaseViewHolder(v)

    inner class HeaderViewHolder(v: View) : HomeViewHolder(v) {

        init {
            ivSideBar.setOnClickListener {
                mainActivity.onDrawerClicked(it)

            }

            ivSearch.setOnClickListener {
                mainActivity.onSearchClicked(it)
            }
        }

        override fun bind(position: Int) {
            lifecycle.addObserver(vYoutubePlayer)
            vYoutubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    try {
                        mHomeData.magazines[0].youtubeUrl?.split("embed/")?.get(1)?.also {
                            youTubePlayer.loadVideo(it, 0f)
                        } ?: also {
                            homeViewModel.onError("Video Not Available")
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            })
        }


    }

    inner class CategoryViewHolder(v: View) : HomeViewHolder(v) {
        init {
            btnCategories.setOnClickListener {
                homeViewModel.onMoreCategoriesClicked()
            }

            rvCategories.adapter = CategoryAdapter(mHomeData.categories, homeViewModel)

        }

        override fun bind(position: Int) {

        }
    }

    inner class SubjectViewHolder(v: View) : HomeViewHolder(v) {

        init {
            rvSubjects.adapter = HomeSubjectAdapter(mHomeData.articles, homeViewModel)
        }

        override fun bind(position: Int) {

        }
    }

    inner class MagazineViewHolder(v: View) : HomeViewHolder(v) {
        init {
            btnMagazines.setOnClickListener {
                homeViewModel.onMoreMagazinesClicked()
            }
            rvMagazines.adapter = MagazineAdapter(
                mHomeData.magazines,
                false,
                onMagazineClick = {
                    homeViewModel.onMagazineClicked(it)
                })
        }

        override fun bind(position: Int) {
        }
    }

    inner class AuthorViewHolder(v: View) : HomeViewHolder(v) {
        init {
            rvAuthors.adapter = AuthorAdapter(mHomeData.authors) {
                homeViewModel.onAuthorClicked(it.id)
                articleAdapter.authorId = it.id
            }
        }

        override fun bind(position: Int) {

        }
    }

    inner class ArticleViewHolder(v: View) : HomeViewHolder(v) {
        init {
            rvArticles.adapter = articleAdapter
        }

        override fun bind(position: Int) {

        }
    }

    fun updateArticlesByAuthor(articles: List<Article>) =
        articleAdapter.updateArticlesByAuthor(articles)

}