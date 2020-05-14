package alshariqa.althaqafiya.magazine.ui.article.comments

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class WriteCommentFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_write_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adjustPanKeyBoard()
    }

    override fun isBottomBarShown(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String? {
        return getString(R.string.write_comment)
    }

    /* override fun onError(error: String) {
         showMessage(error)
         hideProgressBar()

     }*/


/*    private fun hideProgressBar(show: Boolean = false) {
        progress.visibility = if (show) View.VISIBLE
        else View.GONE
    }*/

    companion object {
        fun newInstance() = WriteCommentFragment()
    }


}