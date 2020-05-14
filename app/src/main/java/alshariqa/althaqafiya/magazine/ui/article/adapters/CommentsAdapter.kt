package alshariqa.althaqafiya.magazine.ui.article.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CommentsAdapter :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_comments, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {


        override fun bind(position: Int) {

        }


    }
}