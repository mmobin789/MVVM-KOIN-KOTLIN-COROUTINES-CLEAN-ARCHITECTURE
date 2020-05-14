package alshariqa.althaqafiya.magazine.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(t: T): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(t: T)

    @Delete
    suspend fun delete(t: T): Int
}