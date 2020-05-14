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
import kotlinx.android.synthetic.main.adapter_subject.*

class HomeSubjectAdapter(
    private val articles: List<Article>,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.Adapter<HomeSubjectAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL

        }
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_subject, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            itemView.setOnClickListener {
                homeViewModel.onArticleClicked(articles[adapterPosition])
            }

        }

        override fun bind(position: Int) {
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
}