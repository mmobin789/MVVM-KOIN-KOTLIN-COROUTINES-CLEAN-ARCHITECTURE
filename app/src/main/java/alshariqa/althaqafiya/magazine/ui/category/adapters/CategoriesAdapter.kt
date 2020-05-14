package alshariqa.althaqafiya.magazine.ui.category.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.category.business.CategoryViewModel
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_home_subject.*

class CategoriesAdapter(
    private val categories: List<Categories.Data>,
    private val categoryViewModel: CategoryViewModel
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    init {
        Log.d(javaClass.simpleName, "Category Count:$itemCount")
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_home_subject, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            btnSeeAll.setOnClickListener {
                categoryViewModel.onCategoryClick(categories[adapterPosition].categoryId)
            }


        }

        override fun bind(position: Int) {
            val category = categories[position]
            tvCategory.text = category.categoryName
            rvSubjects.adapter = CategorySubjectAdapter(category.articles, categoryViewModel)

        }
    }
}