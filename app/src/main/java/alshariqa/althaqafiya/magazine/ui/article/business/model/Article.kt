package alshariqa.althaqafiya.magazine.ui.article.business.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Article(
    @SerializedName("ThumbnailImageFilePath")
    val thumbNail: String?,
    @SerializedName("AuthorName")
    val authorName: String,
    @SerializedName("AuthorPhoto")
    val authorPhoto: String,
    @SerializedName("AuthorId")
    val authorId: Int?,
    @SerializedName("ThumbnailImageFilePath_SmallSquare")
    val imageSmallSquare: String?,
    @SerializedName("ThumbnailImageFilePath_SmallWide")
    val imageSmallWide: String?,
    @SerializedName("HitCounter")
    val hitCounter: Int,
    @PrimaryKey
    @SerializedName("Id")
    val id: Int,
    val isActive: Boolean,
    @SerializedName("Featured")
    val isFeatured: Boolean,
    @SerializedName("PublishingDate")
    val publishDate: String?,
    @SerializedName("IssueNumber")
    val issueNo: String?,
    @SerializedName("CategoryId")
    val categoryId: Int?,
    @SerializedName("ArticleTitle")
    val title: String?,
    @SerializedName("ShortDescription")
    val shortDescription: String?,
    @SerializedName("ArticleDetail")
    val detail: String?,
    @SerializedName("MainImageFilePath")
    val mainImage: String?,
    @SerializedName("SubTitle")
    val subTitle: String?,
    @SerializedName("CategoryName") val categoryName: String?,
    @SerializedName("Rating")
    val rating: Float?

) : Parcelable {
    @IgnoredOnParcel
    var isLiked = false
}
