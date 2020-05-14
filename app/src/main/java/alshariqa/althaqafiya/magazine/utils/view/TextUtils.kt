package alshariqa.althaqafiya.magazine.utils.view

import android.os.Build
import android.text.Html
import android.widget.TextView

fun TextView.loadHTML(s: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(s)
    }
}