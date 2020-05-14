package alshariqa.althaqafiya.magazine.ui.home.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.home.business.HomeViewModel
import alshariqa.althaqafiya.magazine.ui.home.business.model.Category
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_category.*

class CategoryAdapter(
    private val categories: List<Category>,
    private val homeViewModel: HomeViewModel
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    /* private lateinit var allCategories: List<Category>
     private var allCategoriesLoaded = false*/

    /*  fun onScroll(dy: Int) {
          if (dy > 0 && !allCategoriesLoaded) {
              categories = allCategories
              notifyDataSetChanged()
              allCategoriesLoaded = true
          }
      }

      fun setAllCategories(categories: List<Category>) {
          allCategories = categories
      }*/

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
                homeViewModel.onCategoryClicked(categories[adapterPosition].id)
            }
        }

        override fun bind(position: Int) {
            categories[position].run {
                tvCategory.text = title
                ImageLoader.load(imageUrl, ivCategory, R.drawable.ic_ph_category)
            }

        }
    }
}