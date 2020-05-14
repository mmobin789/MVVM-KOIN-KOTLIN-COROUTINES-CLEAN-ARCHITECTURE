package alshariqa.althaqafiya.magazine.utils.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import kotlin.math.roundToInt


fun Any.dpToPixel(value: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, value, context.resources
            .displayMetrics
    ).roundToInt()
}

fun scaleViewRelativeToCenter(vararg v: View, onAnimationCompleted: () -> Unit) {
    val fadeIn = ScaleAnimation(
        0f,
        1f,
        0f,
        1f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    fadeIn.duration = 3000
    fadeIn.fillAfter = true

    v.forEachIndexed { i, view ->
        view.startAnimation(fadeIn)
        view.animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                if (i == v.lastIndex)
                    onAnimationCompleted()
            }
        })
    }
}