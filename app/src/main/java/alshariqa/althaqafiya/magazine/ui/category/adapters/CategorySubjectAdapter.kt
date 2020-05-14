package alshariqa.althaqafiya.magazine.ui.category.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_subject.*
import kotlinx.android.synthetic.main.adapter_subject_centered.*

class CategorySubjectAdapter(
    private val articles: List<Article>,
    private val categoryViewModel: CategoryViewModel? = null,
    private val verticalView: Boolean = false,
    private val onOptionsClick: ((Article) -> Unit)? = null
) :
    RecyclerView.Adapter<CategorySubjectAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context).also {
            it.orientation = if (verticalView)
                LinearLayoutManager.VERTICAL
            else LinearLayoutManager.HORIZONTAL

        }

        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutId = R.layout.adapter_subject
        if (verticalView)
            layoutId = R.layout.adapter_subject_centered

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            if (verticalView) {
                tvDescC.visibility = View.VISIBLE

                tvDescC.setOnClickListener {
                    categoryViewModel?.onArticleClicked(articles[adapterPosition])
                }

                onOptionsClick?.also { onClick ->
                    ivOptions.visibility = View.VISIBLE
                    ivOptions.setOnClickListener {
                        onClick(articles[adapterPosition])
                    }
                }
            } else {
                tvDesc.visibility = View.VISIBLE

                tvDesc.setOnClickListener {
                    categoryViewModel?.onArticleClicked(articles[adapterPosition])
                }
            }

        }

        override fun bind(position: Int) {

            if (verticalView)
                verticalBind(position)
            else horizontalBind(position)
        }

        private fun verticalBind(position: Int) = articles[position].run {
            ImageLoader.load(mainImage, ivArticleC, R.drawable.ic_ph_home_subject)
            ImageLoader.load(authorPhoto, ivAuthorC, R.drawable.ic_ph_author)
            tvTitleC.text = title
            tvSubTitleC.text = subTitle
            tvContentC.text = shortDescription
            tvAuthorC.text = authorName
            tvIssueNoC.text = issueNo

        }

        private fun horizontalBind(position: Int) =
            articles[position].run {
                ImageLoader.load(mainImage, ivArticle, R.drawable.ic_ph_home_subject)
                ImageLoader.load(authorPhoto, ivAuthor, R.drawable.ic_ph_author)
                tvTitle.text = title
                tvSubTitle.text = subTitle
                tvContent.text = shortDescription
                tvAuthor.text = authorName
                tvIssueNo.text = issueNo

            }
    }
}