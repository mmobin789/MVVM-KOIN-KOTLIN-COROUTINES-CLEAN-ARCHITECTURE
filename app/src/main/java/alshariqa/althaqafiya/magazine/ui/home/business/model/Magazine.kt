package alshariqa.althaqafiya.magazine.ui.home.business.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Magazine(
    @SerializedName("MagazineId")
    @PrimaryKey
    val id: Int,
    @SerializedName("MagazineTitle")
    val title: String?,
    @SerializedName("MainImagePath")
    val mainImage: String?,
    @SerializedName("ThumbnailPath")
    val thumbnail: String?,
    @SerializedName("IssueNumber")
    val issueNo: Int?,
    @SerializedName("GeorgianPublishMonth")
    val publishMonth: Int?,
    @SerializedName("GeorgianPublishYear")
    val publishYear: Int?,
    @SerializedName("MainMagazineFilePath")
    val pdfPath: String?,
    @SerializedName("YoutubeUrl")
    val youtubeUrl: String?
) : Parcelable {
    @IgnoredOnParcel
    var isLiked = false
}