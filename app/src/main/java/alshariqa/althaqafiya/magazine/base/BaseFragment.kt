package alshariqa.althaqafiya.magazine.base

import alshariqa.althaqafiya.magazine.ui.main.MainActivity
import alshariqa.althaqafiya.magazine.ui.main.OnScreenTouchListener
import alshariqa.althaqafiya.magazine.ui.main.Settings
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), OnScreenTouchListener {
    protected val mainActivity by lazy {
        requireActivity() as MainActivity
    }

    protected val mainViewModel by lazy {
        mainActivity.mMainViewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.showBottomBar(isBottomBarShown())
        mainViewModel.showToolBar(getToolBarTitle())
        mainActivity.applySettings(getActivitySettings())
        mainActivity.onScreenTouchListener = this

    }

    override fun onScreenTouched(ev: MotionEvent) {

    }

    protected fun adjustPanKeyBoard() =
        mainActivity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


    open fun isBottomBarShown() = true

    open fun getToolBarTitle(): String? = null

    open fun getActivitySettings() = Settings.NONE


    protected fun showMessage(message: String) = mainViewModel.showMessage(message)

    protected fun showMessage(@StringRes stringId: Int) = mainActivity.showToast(stringId)


}