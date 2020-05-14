package alshariqa.althaqafiya.magazine.api

import alshariqa.althaqafiya.magazine.base.logE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface OnApiResponseCallback<T> : Callback<T> {
    private val mTag: String
        get() = javaClass.simpleName

    override fun onFailure(call: Call<T>, t: Throwable) {
        logE(mTag, t.toString().also { onFail(it) })
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onSuccess(response.body())
    }

    fun onSuccess(response: T?)

    fun onFail(error: String)
}