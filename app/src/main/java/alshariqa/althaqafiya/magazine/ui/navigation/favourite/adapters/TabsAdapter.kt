package alshariqa.althaqafiya.magazine.ui.navigation.favourite.adapters

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.ArticleTabFragment
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.CategoryTabFragment
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.FavouriteFragment
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.MagazineTabFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapter(private val favouriteFragment: FavouriteFragment) :
    FragmentStateAdapter(favouriteFragment) {

    private val categoryTabFragment = CategoryTabFragment.newInstance()
    private val articleTabFragment = ArticleTabFragment.newInstance()
    private val magazineTabFragment = MagazineTabFragment.newInstance()

    override fun getItemCount(): Int {
        return 3
    }

    fun getTabText(position: Int) = when (position) {
        0 -> favouriteFragment.getString(R.string.subjects)
        1 -> favouriteFragment.getString(R.string.articles)
        else -> favouriteFragment.getString(R.string.magazines)
    }

    fun getTabIcon(position: Int) = when (position) {
        0 -> R.drawable.tab_1
        1 -> R.drawable.tab_2
        else -> R.drawable.tab_3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> categoryTabFragment
            1 -> articleTabFragment
            else -> magazineTabFragment
        }
    }
}