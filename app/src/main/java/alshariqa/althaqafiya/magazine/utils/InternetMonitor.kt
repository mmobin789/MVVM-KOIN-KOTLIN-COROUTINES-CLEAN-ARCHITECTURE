package alshariqa.althaqafiya.magazine.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build

/**
 * An internet monitor class for Android API Lollipop and above.
 * @param onInternetConnectivityListener A listener for states.
 * @author Mobin Munir
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class InternetMonitor(private val onInternetConnectivityListener: OnInternetConnectivityListener) {

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {

            onInternetConnectivityListener.onInternetAvailable()
        }

        override fun onLost(network: Network) {
            onInternetConnectivityListener.onInternetLost()
        }

        override fun onUnavailable() {
            onInternetConnectivityListener.onInternetLost()
        }


    }

    /**
     * The method which registers checks for network changes.
     * @param context
     * Note: Make sure to unregister.
     */
    fun register(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            connectivityManager.requestNetwork(networkRequest, networkCallback, 1000)
        }
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    }

    /**
     * The method which unregisters checks for network changes.\
     * @param context
     */
    fun unregister(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    /**
     * A listener for callbacks.
     */
    interface OnInternetConnectivityListener {
        fun onInternetAvailable()
        fun onInternetLost()
    }
}