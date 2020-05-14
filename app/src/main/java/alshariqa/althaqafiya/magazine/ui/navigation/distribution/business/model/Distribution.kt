package alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.model

import com.google.gson.annotations.SerializedName

data class Distribution(@SerializedName("Data") val data: List<Data>) {
    inner class Data(
        val latitude: Double,
        val longitude: Double,
        @SerializedName("Name") val name: String
    )
}