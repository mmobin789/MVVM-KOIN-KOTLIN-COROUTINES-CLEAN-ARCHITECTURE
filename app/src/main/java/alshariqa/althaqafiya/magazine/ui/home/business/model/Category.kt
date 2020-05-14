package alshariqa.althaqafiya.magazine.ui.home.business.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("CategoryTitle")
    val title: String?,
    @SerializedName("ImageUrl")
    val imageUrl: String?
)