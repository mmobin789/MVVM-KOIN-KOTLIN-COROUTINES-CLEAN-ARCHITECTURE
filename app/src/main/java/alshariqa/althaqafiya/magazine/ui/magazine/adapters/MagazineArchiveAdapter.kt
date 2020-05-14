package alshariqa.althaqafiya.magazine.ui.magazine.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseViewHolder
import alshariqa.althaqafiya.magazine.ui.home.adapters.MagazineAdapter
import alshariqa.althaqafiya.magazine.ui.magazine.business.MagazineViewModel
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchive
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineArchiveHeader
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.MagazineYearly
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_magazine_archive_header.*
import kotlinx.android.synthetic.main.adapter_magazine_tab.*


class MagazineArchiveAdapter(
    private val magazineViewModel: MagazineViewModel,
    private var magazines: ArrayList<MagazineYearly>,
    private val onFilterClickListener: OnFilterClickListener
) :
    RecyclerView.Adapter<MagazineArchiveAdapter.ViewHolder>() {


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastIndex = magazines.lastIndex
                if (layoutManager.findLastVisibleItemPosition() == lastIndex) {
                    magazineViewModel.loadMagazines()
                }
            }
        })
    }

    /*  private fun loading(loading: Boolean = true) {
          val lastIndex = itemCount - 1
          if (loading) {
              magazines.add(null)
              notifyItemInserted(lastIndex)
          } else {
              magazines.removeAt(lastIndex)
              notifyItemRemoved(lastIndex)
          }
      }*/

    fun update(
        magazines: ArrayList<MagazineArchive>,
        clear: Boolean = false,
        filter1: String = "",
        filter2: String = ""
    ) {
        if (clear) {
            this.magazines.clear()
            this.magazines.add(MagazineArchiveHeader(filter1, filter2))
            this.magazines.addAll(magazines)
            notifyDataSetChanged()
        } else {
            this.magazines.addAll(magazines)
            notifyItemRangeInserted(itemCount, this.magazines.size)
        }


    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return 0
        return 1
    }

    override fun getItemCount(): Int {
        return magazines.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == 0)
            return HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_magazine_archive_header, parent, false)
            )
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_magazine_tab, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                (holder as HeaderViewHolder).bind(position)
            }
            else -> holder.bind(position)
        }
    }

    inner class HeaderViewHolder(v: View) : ViewHolder(v) {

        init {
            etMonth.setOnClickListener {
                onFilterClickListener.onMonthClick(it)
            }
            etYear.setOnClickListener {
                onFilterClickListener.onYearClick(it)
            }
        }

        override fun bind(position: Int) {
            (magazines[position] as MagazineArchiveHeader).run {
                etYear.setText(year)
                etMonth.setText(month)
            }
        }
    }

    interface OnFilterClickListener {
        fun onMonthClick(v: View)
        fun onYearClick(v: View)
    }

    open inner class ViewHolder(v: View) : BaseViewHolder(v) {
        override fun bind(position: Int) {
            (magazines[position] as MagazineArchive).run {
                rv.adapter = MagazineAdapter(
                    magazines, true,
                    onMagazineClick = {
                        magazineViewModel.onMagazineClick(it)
                    }, onOptionsClicked = { magazineViewModel.onMagazineOptionsClick(it) },
                    magazineViewModel = magazineViewModel
                )
                tvYear.text = year.toString()
            }


        }
    }
}