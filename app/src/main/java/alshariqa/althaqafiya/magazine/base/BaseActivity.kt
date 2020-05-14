package alshariqa.althaqafiya.magazine.base

import alshariqa.althaqafiya.magazine.base.di.DIFramework
import alshariqa.althaqafiya.magazine.utils.InternetMonitor
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * An abstract base activity class to share common behavior among children activities.
 */
abstract class BaseActivity : AppCompatActivity() {


    /* private val mNotificationsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
         override fun onReceive(context: Context, intent: Intent) {
             onPushNotification(intent)
         }
     }*/

    //  private val intentFilter = IntentFilter(NotificationUtils.PUSH_NOTIFICATION)

    /*  protected open fun onPushNotification(intent: Intent?) {
      }*/


    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            showInternetPopUp(true)
        }

        override fun onInternetLost() {
            showInternetPopUp()
        }
    })


    private fun showInternetPopUp(dismiss: Boolean = false) {
        runOnUiThread {
            if (internetDialog?.isShowing == true)
                internetDialog?.dismiss()
            if (!dismiss)
                internetDialog?.show()
        }
    }

    /**
     * A thread-safe method to show a short-length toast on UI.
     * @param message A message to display.
     */
    protected fun showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    /**
     * A method to show a short-length toast on UI.
     * @param id A must-be valid string resource id to display else an exception will be thrown.
     */
    fun showToast(@StringRes id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        init()


    }


    private fun init() {
        if (internetDialog == null)
            internetDialog = AlertDialog.Builder(this).setTitle("No Connectivity!")
                .setMessage("Check your mobile data or WiFi connection then try again.")
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
                .create()


        DIFramework.init(this)
    }

    override fun onResume() {
        super.onResume()
        //    registerReceiver(mNotificationsReceiver, intentFilter)
        internetMonitor.register(this)
    }

    override fun onPause() {
        super.onPause()
        //unregisterReceiver(mNotificationsReceiver)
        internetMonitor.unregister(this)
    }

    protected fun getResColor(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

    companion object {
        private var internetDialog: AlertDialog? = null
    }
}