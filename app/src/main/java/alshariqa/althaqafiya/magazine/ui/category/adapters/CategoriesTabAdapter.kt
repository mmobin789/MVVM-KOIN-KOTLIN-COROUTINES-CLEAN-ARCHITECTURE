package alshariqa.althaqafiya.magazine.ui.category.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_categories.*
import kotlinx.android.synthetic.main.adapter_categories_header.*

class CategoriesTabAdapter(
    private val categories: List<Categories.Data>,
    private val categoryViewModel: CategoryViewModel
) :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val categoryViewType = 0

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return categoryViewType
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        if (viewType == categoryViewType)
            return CategoryViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_categories_header, parent, false)
            )
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_categories, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (getItemViewType(position)) {
            categoryViewType -> (holder as CategoryViewHolder).bind(position)
            else -> holder.bind(position)
        }
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            rvCategories.adapter = CategoriesAdapter(categories, categoryViewModel)

        }

        override fun bind(position: Int) {

        }
    }

    inner class CategoryViewHolder(v: View) : BaseViewHolder(v) {
        init {
            rvCategoriesHeader.adapter = CategoryAdapter(categories, categoryViewModel)
        }

        override fun bind(position: Int) {

        }
    }
}