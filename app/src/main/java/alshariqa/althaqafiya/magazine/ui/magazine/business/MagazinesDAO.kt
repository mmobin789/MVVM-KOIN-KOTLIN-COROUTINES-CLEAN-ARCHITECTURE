package alshariqa.althaqafiya.magazine.ui.magazine.business

import alshariqa.althaqafiya.magazine.base.BaseDAO
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface MagazinesDAO : BaseDAO<Magazine> {
    @Query("SELECT * FROM magazine WHERE isLiked")
    fun getFavoriteMagazines(): LiveData<List<Magazine>>

    @Query("SELECT * FROM magazine WHERE id==:id")
    suspend fun getMagazine(id: Int): Magazine?
}