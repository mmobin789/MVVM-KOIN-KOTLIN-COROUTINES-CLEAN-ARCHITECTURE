package alshariqa.althaqafiya.magazine.ui.article.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_article.*


class ArticlesByAuthorAdapter(
    private val articles: List<Article>,
    private val articlesViewModel: ArticlesViewModel? = null,
    private val onOptionsClick: ((Article) -> Unit)? = null
) :
    RecyclerView.Adapter<ArticlesByAuthorAdapter.ViewHolder>() {


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
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

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {

            onOptionsClick?.also { onClick ->
                ivOptions.visibility = View.VISIBLE
                ivOptions.setOnClickListener {
                    onClick(articles[adapterPosition])
                }
            }


            itemView.setOnClickListener {
                articlesViewModel?.onArticleClick(articles[adapterPosition])
            }
        }

        override fun bind(position: Int) {
            articles[position].run {
                ImageLoader.load(
                    articlesViewModel?.author?.thumb,
                    ivAuthor,
                    R.drawable.ic_ph_author
                )
                tvDate.text = publishDate
                tvTitle.text = title
                tvAuthor.text = authorName

            }


        }
    }
}