package alshariqa.althaqafiya.magazine.ui.navigation.contact.business.model

import com.google.gson.annotations.SerializedName

data class ContactInfo(@SerializedName("Data") val data: Data) {
    inner class Data(
        val latitude: Double,
        val longitude: Double,
        @SerializedName("Content") val content: String
    )
}