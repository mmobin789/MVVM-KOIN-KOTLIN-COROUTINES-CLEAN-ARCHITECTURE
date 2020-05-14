package alshariqa.althaqafiya.magazine.ui.navigation.settings

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ivBack.setOnClickListener {
            mainActivity.onBackClicked(it)
        }
        ivSideBar.setOnClickListener {
            mainActivity.onDrawerClicked(it)
        }
        sw1.setOnClickListener {
            sw1.setImageResource(
                if (it.isSelected) {
                    it.isSelected = false
                    R.drawable.ic_switch_off
                } else {
                    it.isSelected = true
                    R.drawable.ic_switch_on
                }
            )

        }
        sw2.setOnClickListener {
            sw2.setImageResource(
                if (it.isSelected) {
                    it.isSelected = false
                    R.drawable.ic_switch_off
                } else {
                    it.isSelected = true
                    R.drawable.ic_switch_on
                }
            )
        }
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
        fun newInstance() = SettingsFragment()
    }


}