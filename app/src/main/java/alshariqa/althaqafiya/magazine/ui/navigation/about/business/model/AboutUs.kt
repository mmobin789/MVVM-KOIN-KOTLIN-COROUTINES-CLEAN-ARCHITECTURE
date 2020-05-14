package alshariqa.althaqafiya.magazine.ui.navigation.about.business.model

import com.google.gson.annotations.SerializedName

data class AboutUs(@SerializedName("Data") val data: Data) {
    inner class Data(
        @SerializedName("Content") val content: String
    )
}