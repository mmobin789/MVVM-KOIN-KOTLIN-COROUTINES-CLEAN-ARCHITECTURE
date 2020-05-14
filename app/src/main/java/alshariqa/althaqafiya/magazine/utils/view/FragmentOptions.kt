package alshariqa.althaqafiya.magazine.utils.view

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseActivity
import alshariqa.althaqafiya.magazine.base.BaseFragment
import alshariqa.althaqafiya.magazine.ui.main.MainActivity


//private lateinit var mOnFragmentBackPressListener: OnFragmentBackPressListener

fun BaseActivity.loadFragment(
    baseFragment: BaseFragment,
    addToBackStack: Boolean = false,
    replace: Boolean = false
) {
    supportFragmentManager.beginTransaction().run {
        val name = baseFragment.javaClass.simpleName
        if (replace)
            replace(R.id.container, baseFragment, name)
        else
            add(R.id.container, baseFragment, name)

        if (addToBackStack)
            addToBackStack(name)

        commit()

    }
}


fun MainActivity.handleOnBackPressed() {
    supportFragmentManager.findFragmentById(R.id.container)?.also {
        val baseFragment = it as BaseFragment
        applySettings(baseFragment.getActivitySettings())
        showToolBar(baseFragment.getToolBarTitle())
        showBottomBar(baseFragment.isBottomBarShown())
        //    mOnFragmentBackPressListener.onFragmentBackPressed(baseFragment)
    }


}

/*fun setOnFragmentBackPressListener(onFragmentBackPressListener: OnFragmentBackPressListener) {
    mOnFragmentBackPressListener = onFragmentBackPressListener
}

interface OnFragmentBackPressListener {
    fun onFragmentBackPressed(baseFragment: BaseFragment)
}*/


