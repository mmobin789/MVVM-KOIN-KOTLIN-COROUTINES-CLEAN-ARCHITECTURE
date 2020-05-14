package alshariqa.althaqafiya.magazine.ui.magazine.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MagazinesTabAdapter(
    private val magazines: List<Magazine>,
    private val onMagazineClick: (Magazine) -> Unit
) :
    RecyclerView.Adapter<MagazinesTabAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return magazines.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_magazine_tab, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            itemView.setOnClickListener {
                onMagazineClick(magazines[adapterPosition])
            }
        }

        override fun bind(position: Int) {
            magazines[position].run {


            }


        }
    }
}