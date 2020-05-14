package alshariqa.althaqafiya.magazine.ui.magazine.business.model

import alshariqa.althaqafiya.magazine.base.BaseResponse
import com.google.gson.annotations.SerializedName

data class Magazines(@SerializedName("Data") val data: Data) : BaseResponse() {
    inner class Data(@SerializedName("lstMagazineModelYearWise") val magazineArchives: ArrayList<MagazineArchive>)
}