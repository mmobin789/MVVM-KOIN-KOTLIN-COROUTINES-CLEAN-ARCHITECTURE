package alshariqa.althaqafiya.magazine.ui.search.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.ui.search.ArticleTabFragment
import alshariqa.althaqafiya.magazine.ui.search.CategoryTabFragment
import alshariqa.althaqafiya.magazine.ui.search.MagazineTabFragment
import alshariqa.althaqafiya.magazine.ui.search.SearchFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapter(private val searchFragment: SearchFragment) :
    FragmentStateAdapter(searchFragment) {

    override fun getItemCount(): Int {
        return 3
    }

    fun getTabText(position: Int) = when (position) {
        0 -> searchFragment.getString(R.string.subjects)
        1 -> searchFragment.getString(R.string.articles)
        else -> searchFragment.getString(R.string.magazines)
    }

    fun getTabIcon(position: Int) = when (position) {
        0 -> R.drawable.tab_1
        1 -> R.drawable.tab_2
        else -> R.drawable.tab_3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CategoryTabFragment.newInstance()
            1 -> ArticleTabFragment.newInstance()
            else -> MagazineTabFragment.newInstance()
        }
    }
}