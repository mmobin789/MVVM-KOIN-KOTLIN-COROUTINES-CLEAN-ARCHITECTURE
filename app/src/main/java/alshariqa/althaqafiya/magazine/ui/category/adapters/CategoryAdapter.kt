package alshariqa.althaqafiya.magazine.ui.category.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import alshariqa.althaqafiya.magazine.ui.home.adapters.CategorySpaceItemDecoration
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_category.*

class CategoryAdapter(
    private val categories: List<Categories.Data>,
    private val categoryViewModel: CategoryViewModel
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.run {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(CategorySpaceItemDecoration(32))
            setHasFixedSize(true)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            itemView.setOnClickListener {
                categoryViewModel.onCategoryClick(categories[adapterPosition].categoryId)
            }
        }

        override fun bind(position: Int) {
            categories[position].run {
                tvCategory.text = categoryName
                ImageLoader.load(imageUrl, ivCategory, R.drawable.ic_ph_category)
            }

        }
    }
}