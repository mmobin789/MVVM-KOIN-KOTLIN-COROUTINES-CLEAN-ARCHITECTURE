package alshariqa.althaqafiya.magazine.ui.article

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.utils.view.ImageLoader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_slider.*


class SliderFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.run {
            val position = getInt("p")
            tvIssueNo.text = position.toString()
        }

        ImageLoader.load(null, ivArticle, R.drawable.ic_ph_subject_carousel)
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }


    companion object {
        fun newInstance() = SliderFragment()
    }


}