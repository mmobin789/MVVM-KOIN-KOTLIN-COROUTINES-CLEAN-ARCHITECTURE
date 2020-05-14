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
import kotlinx.android.synthetic.main.adapter_subject.*
import kotlinx.android.synthetic.main.adapter_subject_single.*


class ArticlesAdapter(
    private val articles: List<Article>,
    private val articlesViewModel: ArticlesViewModel,
    private val horizontalView: Boolean = false
) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context).also {
            if (horizontalView)
                it.orientation = LinearLayoutManager.HORIZONTAL
        }

        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutId = R.layout.adapter_subject_single
        if (horizontalView)
            layoutId = R.layout.adapter_subject
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {
        init {
            itemView.setOnClickListener {
                if (!horizontalView)
                    articlesViewModel.onArticleClick(articles[adapterPosition])
            }
        }

        private fun horizontalBind(article: Article) = article.run {
            ImageLoader.load(mainImage, ivArticle, R.drawable.ic_ph_home_subject)
            ImageLoader.load(authorPhoto, ivAuthor, R.drawable.ic_ph_author)
            tvTitle.text = title
            tvSubTitle.text = subTitle
            tvAuthor.text = authorName
            tvContent.text = shortDescription
        }

        override fun bind(position: Int) {
            articles[position].run {
                if (horizontalView) {
                    horizontalBind(this)
                } else {
                    ImageLoader.load(mainImage, ivArticleS, R.drawable.ic_ph_home_subject)
                    ImageLoader.load(authorPhoto, ivAuthorS, R.drawable.ic_ph_author)
                    tvTitleS.text = title
                    tvSubTitleS.text = subTitle
                    tvAuthorS.text = authorName
                    tvContentS.text = shortDescription
                }

            }


        }
    }
}