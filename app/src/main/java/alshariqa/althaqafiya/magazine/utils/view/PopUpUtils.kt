package alshariqa.althaqafiya.magazine.utils.view

import alshariqa.althaqafiya.magazine.R
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import kotlinx.android.synthetic.main.layout_add_to_favourite.view.*
import kotlinx.android.synthetic.main.layout_favorite_options_popup.view.*
import kotlinx.android.synthetic.main.layout_filter_dialog.view.*
import kotlinx.android.synthetic.main.layout_magazine_options_dialog.view.*
import kotlinx.android.synthetic.main.layout_rating_dialog.view.*
import kotlinx.android.synthetic.main.popup_font_size.view.*


object PopUpUtils {

    fun showRatingPopUp(v: View) {
        val popupWindow = PopupWindow(
            View.inflate(v.context, R.layout.layout_rating_dialog, null),
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0)
        dimBehind(popupWindow)

        popupWindow.contentView.ivBack.setOnClickListener {
            popupWindow.dismiss()
        }
    }

    fun showFilterPopUp(v: View) {
        val popupWindow = PopupWindow(
            View.inflate(v.context, R.layout.layout_filter_dialog, null),
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )
        //todo need to add dynamic menus to show months/years in arabic.
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0)
        dimBehind(popupWindow)

        popupWindow.contentView.ivBack1.setOnClickListener {
            popupWindow.dismiss()
        }
    }

    fun showFontSizePopUp(
        v: View,
        onShown: () -> Unit,
        fontSize: (Boolean) -> Unit
    ) = PopupWindow(
        View.inflate(v.context, R.layout.popup_font_size, null),
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT, true
    ).apply {
        contentView.ivFontPlus.setOnClickListener {
            fontSize(true)
        }

        contentView.ivFontMinus.setOnClickListener {
            fontSize(false)
        }

        showAtLocation(v, Gravity.END, v.top, v.right)
        onShown()

    }


    fun showAddToFavouritePopUpWindow(v: View) {
        PopupWindow(
            View.inflate(v.context, R.layout.layout_add_to_favourite, null),
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        ).apply {
            showAtLocation(v, Gravity.BOTTOM, 0, v.bottom * 2)

            contentView.ivCross.setOnClickListener {
                dismiss()
            }

        }
    }

    fun showMagazineOptionsPopUp(v: View, onRead: () -> Unit) {
        val popupWindow = PopupWindow(
            View.inflate(v.context, R.layout.layout_magazine_options_dialog, null),
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )

        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0)
        dimBehind(popupWindow)

        popupWindow.contentView.ivBack2.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.contentView.tvIssueNo.setOnClickListener {
            onRead()
            popupWindow.dismiss()
        }
    }

    fun showFavoriteOptions(v: View, onRead: () -> Unit, onDelete: () -> Unit) {
        val popupWindow = PopupWindow(
            View.inflate(v.context, R.layout.layout_favorite_options_popup, null),
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, true
        )

        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0)
        dimBehind(popupWindow)
        val windowV = popupWindow.contentView

        windowV.ivBack3.setOnClickListener {
            popupWindow.dismiss()
        }
        windowV.tvDelete.setOnClickListener {
            onDelete()
            popupWindow.dismiss()
        }

        windowV.tvRead.setOnClickListener {
            onRead()
            popupWindow.dismiss()
        }
    }
    /* val dialog = Dialog(v.context)
     val window = dialog.window
     val windowAttributes = window?.attributes
     windowAttributes?.width = ViewGroup.LayoutParams.MATCH_PARENT
     windowAttributes?.gravity = Gravity.BOTTOM
     dialog.window?.attributes = windowAttributes
     dialog.setContentView(R.layout.layout_rating_dialog)
     dialog.setCancelable(true)
     dialog.setCanceledOnTouchOutside(true)
     dialog.ivBack.setOnClickListener {
         dialog.dismiss()
     }
     dialog.show()*/


    private fun dimBehind(popupWindow: PopupWindow) {
        val container = popupWindow.contentView.rootView
        val context = popupWindow.contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container?.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.3f
        wm.updateViewLayout(container, p)
    }
}