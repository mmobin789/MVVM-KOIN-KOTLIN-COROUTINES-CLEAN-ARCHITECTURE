package alshariqa.althaqafiya.magazine.base

import android.util.Log

private const val mTag = "CustomerApp"

/**
 * Logs a debug.
 * @param tag a nullable tag uses app name by default.
 * @param message a message.
 * @param exception optional exception to log.
 */
fun Any.logD(tag: String? = mTag, message: String, exception: Exception? = null) {
    exception?.run {
        Log.d(tag, message, this)
    } ?: run {
        Log.d(tag, message)
    }
}

/**
 * Logs a warning.
 * @param tag a nullable tag uses app name by default.
 * @param message a message.
 * @param exception optional exception to log.
 */
fun Any.logW(tag: String? = mTag, message: String, exception: Exception? = null) {
    exception?.run {
        Log.w(tag, message, this)
    } ?: run {
        Log.w(tag, message)
    }
}

/**
 * Logs an error.
 * @param tag a nullable tag uses app name by default.
 * @param message a message.
 * @param exception optional exception to log.
 */
fun Any.logE(tag: String? = mTag, message: String, exception: Exception? = null) {
    exception?.run {
        Log.e(tag, message, this)
    } ?: run {
        Log.e(tag, message)
    }
}