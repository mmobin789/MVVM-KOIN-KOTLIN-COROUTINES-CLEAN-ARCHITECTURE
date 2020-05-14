package alshariqa.althaqafiya.magazine.ui.home.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.business.MagazineViewModel
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_magazine.*
import kotlinx.android.synthetic.main.adapter_magazine_grid.*


class MagazineAdapter(
    private val magazines: List<Magazine>,
    private val gridView: Boolean = false,
    private val onMagazineClick: ((Magazine) -> Unit)? = null,
    private val onOptionsClicked: ((Magazine) -> Unit)? = null,
    private val magazineViewModel: MagazineViewModel? = null
) :
    RecyclerView.Adapter<MagazineAdapter.ViewHolder>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        recyclerView.layoutManager = if (gridView) {
            //recyclerView.addItemDecoration(MagazineSpaceItemDecoration(32))
            GridLayoutManager(recyclerView.context, 2)
        } else
            LinearLayoutManager(recyclerView.context).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }

        recyclerView.setHasFixedSize(true)
    }

    override fun getItemCount(): Int {
        return magazines.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (gridView)
            R.layout.adapter_magazine_grid
        else R.layout.adapter_magazine

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(v: View) : BaseViewHolder(v) {

        init {
            if (gridView) {
                ivOptions.setOnClickListener {
                    onOptionsClicked?.invoke(magazines[adapterPosition])
                }

                btnSave.setOnClickListener {
                    magazineViewModel?.also {
                        val position = adapterPosition
                        it.addToFavorite(magazines[position])
                        notifyItemChanged(position)
                    }
                }
            }

            itemView.setOnClickListener {
                onMagazineClick?.invoke(magazines[adapterPosition])
            }
        }

        override fun bind(position: Int) {
            magazines[position].run {
                if (gridView) {
                    ImageLoader.load(thumbnail, ivMagazineG, R.drawable.ic_ph_home_article)
                    tvTitleG.text = title

                    magazineViewModel?.getMagazine(id, {
                        btnSave.visibility = View.GONE
                    }, {
                        btnSave.visibility = View.VISIBLE
                    })


                } else {
                    ImageLoader.load(thumbnail, ivMagazine, R.drawable.ic_ph_home_article)
                    tvTitle.text = title
                }

            }


        }
    }
}