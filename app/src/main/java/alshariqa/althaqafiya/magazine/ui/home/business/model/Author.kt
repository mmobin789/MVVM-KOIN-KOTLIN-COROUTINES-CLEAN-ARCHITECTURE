package alshariqa.althaqafiya.magazine.ui.home.business.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    @SerializedName("AutherID") val id: Int,
    @SerializedName("AutherName") val name: String?,
    @SerializedName("Thumb") val thumb: String?,
    @SerializedName("ArticleCount") val articleCount: Int
) : Parcelable {
    @IgnoredOnParcel
    @Transient
    var isSelected = false
}