package alshariqa.althaqafiya.magazine.ui.article.adapters

import alshariqa.althaqafiya.magazine.ui.article.ArticleDetailFragment
import alshariqa.althaqafiya.magazine.ui.article.SliderFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SliderPagerAdapter(articleDetailFragment: ArticleDetailFragment) :
    FragmentStateAdapter(articleDetailFragment) {

    override fun getItemCount(): Int {
        return 5
    }


    override fun createFragment(position: Int): Fragment {
        return SliderFragment.newInstance().also {
            it.arguments = Bundle().apply {
                // todo add images here.
                putInt("p", position)
            }
        }
    }
}