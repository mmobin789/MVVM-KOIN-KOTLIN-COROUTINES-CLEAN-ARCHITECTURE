package alshariqa.althaqafiya.magazine.ui.magazine.business.model

import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import com.google.gson.annotations.SerializedName

data class MagazineArchive(
    @SerializedName("Year") val year: Int,
    @SerializedName("TotalPageCount") val totalPageCount: Int,
    @SerializedName("MagazineList") val magazines: ArrayList<Magazine>
) : MagazineYearly()