package alshariqa.althaqafiya.magazine.ui.home.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_author.*


class AuthorAdapter(
    private val authors: List<Author>,
    private val onClick: (Author) -> Unit
) :
    RecyclerView.Adapter<AuthorAdapter.ViewHolder>() {
    private var lastSelectedPosition = -1

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL

        }
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_author, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                onClick(authors[position])
                if (lastSelectedPosition > -1) {
                    val magazineWriter = authors[lastSelectedPosition]
                    magazineWriter.isSelected = false
                    notifyItemChanged(lastSelectedPosition)
                }
                val magazineWriter = authors[position]
                magazineWriter.isSelected = true
                notifyItemChanged(position)
                lastSelectedPosition = position


            }
        }

        override fun bind(position: Int) {

            authors[position].run {

                ImageLoader.load(thumb, ivAuthor, R.drawable.ic_ph_author)

                if (isSelected) {
                    ivAuthor.setBackgroundResource(R.drawable.app_author_img_bg)
                    ivTriangle.visibility = View.VISIBLE
                } else {
                    ivAuthor.setBackgroundResource(0)
                    ivTriangle.visibility = View.GONE
                }

            }


        }
    }
}