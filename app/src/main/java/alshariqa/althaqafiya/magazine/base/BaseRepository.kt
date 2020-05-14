package alshariqa.althaqafiya.magazine.base

import alshariqa.althaqafiya.magazine.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import org.koin.java.KoinJavaComponent.inject


abstract class BaseRepository {
    private lateinit var scope: CoroutineScope
    protected val appDatabase: AppDatabase by inject(AppDatabase::class.java)

    fun setScope(scope: CoroutineScope) {
        this.scope = scope
    }

    fun getScope() = scope
}
