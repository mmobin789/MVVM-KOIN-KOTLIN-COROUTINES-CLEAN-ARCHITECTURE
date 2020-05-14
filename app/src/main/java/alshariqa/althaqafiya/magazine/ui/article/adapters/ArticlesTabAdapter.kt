package alshariqa.althaqafiya.magazine.ui.article.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesViewModel
import alshariqa.althaqafiya.magazine.ui.home.adapters.MagazineSpaceItemDecoration
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_tab_articles.*


class ArticlesTabAdapter(
    private val writers: List<Author>,
    private val articlesViewModel: ArticlesViewModel
) :
    RecyclerView.Adapter<ArticlesTabAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
        recyclerView.addItemDecoration(MagazineSpaceItemDecoration(32))
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return writers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_tab_articles, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {
        private val articlesLabel = v.context.getString(R.string.articles)

        init {
            itemView.setOnClickListener {
                articlesViewModel.onAuthorClick(writers[adapterPosition])
            }
        }

        override fun bind(position: Int) = writers[position].run {
            ImageLoader.load(thumb, ivAuthor, R.drawable.ic_ph_author)
            tvAuthor.text = name
            val totalArticles = "$articleCount $articlesLabel"
            tvArticle.text = totalArticles
        }

    }
}