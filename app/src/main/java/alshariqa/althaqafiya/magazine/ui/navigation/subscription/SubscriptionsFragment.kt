package alshariqa.althaqafiya.magazine.ui.navigation.subscription

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_subscriptions.*


class SubscriptionsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_subscriptions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ivBack.setOnClickListener {
            mainActivity.onBackClicked(it)
        }
        ivSideBar.setOnClickListener {
            mainActivity.onDrawerClicked(it)
        }
        tvLabel2.movementMethod = ScrollingMovementMethod.getInstance()
    }

    override fun isBottomBarShown(): Boolean {
        return false
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
        fun newInstance() = SubscriptionsFragment()
    }


}