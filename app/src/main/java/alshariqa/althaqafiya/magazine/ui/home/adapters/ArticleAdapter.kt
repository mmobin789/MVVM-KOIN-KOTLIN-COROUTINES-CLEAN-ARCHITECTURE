package alshariqa.althaqafiya.magazine.ui.home.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.HomeViewModel
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_article.*


class ArticleAdapter(
    private var articles: MutableList<Article>,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var authorId = -1

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        if (articles.size >= 3)
            return 3
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_article, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun updateArticlesByAuthor(articles: List<Article>) {
        this.articles = articles.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {
        private val error = v.context.getString(R.string.select_author)

        init {
            btnArticles.setOnClickListener {
                if (authorId == -1)
                    homeViewModel.onError(error)
                else {
                    homeViewModel.onMoreArticlesByAuthor(authorId)
                }
            }
        }

        override fun bind(position: Int) {

            articles[position].run {
                ImageLoader.load(mainImage, ivAuthor, R.drawable.ic_ph_author)
                tvDate.text = publishDate
                tvTitle.text = title
                tvAuthor.text = authorName

            }

            btnArticles.visibility = if (position == itemCount - 1) {
                View.VISIBLE
            } else View.GONE


        }
    }
}