package alshariqa.althaqafiya.magazine.base

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("Message")
    var message: String? = null

    @SerializedName("Success")
    var success = false

    @SerializedName("Code")
    var code = -1
}