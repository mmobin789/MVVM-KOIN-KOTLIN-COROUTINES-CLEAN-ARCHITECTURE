package alshariqa.althaqafiya.magazine.ui.article.comments

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.article.adapters.CommentsAdapter
import alshariqa.althaqafiya.magazine.ui.article.comments.business.CommentsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_comments.*


class CommentsFragment : BaseFragment(), CommentsViewModel.View {

    private val mCommentsViewModel: CommentsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mCommentsViewModel.let {
            it.attachView(this)
            it.addObservers(this)
        }
        hideProgressBar()
        rv.adapter = CommentsAdapter()
        ll.setOnClickListener {
            mCommentsViewModel.writeComment()
        }
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.comments)
    }

    override fun onError(error: String) {
        showMessage(error)

    }


    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }

    override fun writeComment() {
        mainViewModel.writeComment()
    }

    companion object {
        fun newInstance() = CommentsFragment()
    }


}