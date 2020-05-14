package alshariqa.althaqafiya.magazine.database

import alshariqa.althaqafiya.magazine.ui.article.business.ArticlesDAO
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
import alshariqa.althaqafiya.magazine.ui.magazine.business.MagazinesDAO
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Article::class, Magazine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getArticlesDAO(): ArticlesDAO
    abstract fun getMagazinesDAO(): MagazinesDAO
}