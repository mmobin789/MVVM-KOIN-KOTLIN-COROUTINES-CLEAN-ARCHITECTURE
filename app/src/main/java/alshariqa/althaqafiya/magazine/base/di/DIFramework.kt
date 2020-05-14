package alshariqa.althaqafiya.magazine.base.di

import alshariqa.althaqafiya.magazine.database.AppDatabase
import alshariqa.althaqafiya.magazine.ui.article.ArticleDetailFragment
import alshariqa.althaqafiya.magazine.ui.article.ArticlesFragment
import alshariqa.althaqafiya.magazine.ui.article.comments.CommentsFragment
import alshariqa.althaqafiya.magazine.ui.article.comments.WriteCommentFragment
import alshariqa.althaqafiya.magazine.ui.article.tab.ArticlesByAuthorFragment
import alshariqa.althaqafiya.magazine.ui.article.tab.ArticlesTabFragment
import alshariqa.althaqafiya.magazine.ui.category.CategoriesFragment
import alshariqa.althaqafiya.magazine.ui.category.CategoryDetailFragment
import alshariqa.althaqafiya.magazine.ui.home.HomeFragment
import alshariqa.althaqafiya.magazine.ui.magazine.MagazineDetailsFragment
import alshariqa.althaqafiya.magazine.ui.magazine.MagazineFragment
import alshariqa.althaqafiya.magazine.ui.navigation.about.AboutFragment
import alshariqa.althaqafiya.magazine.ui.navigation.contact.ContactUsFragment
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.DistributionFragment
import alshariqa.althaqafiya.magazine.ui.navigation.editorial.EditorialFragment
import alshariqa.althaqafiya.magazine.ui.navigation.favourite.FavouriteFragment
import alshariqa.althaqafiya.magazine.ui.navigation.settings.SettingsFragment
import alshariqa.althaqafiya.magazine.ui.navigation.subscription.SubscriptionsFragment
import alshariqa.althaqafiya.magazine.ui.search.SearchFragment
import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 */
object DIFramework {

    private var init = false

    fun init(context: Context) {

        if (init)
            return

        /* val repoModule = module {
             single {
                 //   AuthRepository.getInstance()
             }

         }*/

        val fragmentModule = module {
            //factory { SplashFragment.newInstance() }
            factory { HomeFragment.newInstance() }
            factory { CategoriesFragment.newInstance() }
            factory { CategoryDetailFragment.newInstance() }
            factory { MagazineFragment.newInstance() }
            factory { ArticlesTabFragment.newInstance() }
            factory { MagazineDetailsFragment.newInstance() }
            factory { ArticlesFragment.newInstance() }
            factory { ArticlesByAuthorFragment.newInstance() }
            factory { ArticleDetailFragment.newInstance() }
            factory { alshariqa.althaqafiya.magazine.ui.article.tab.ArticleDetailFragment.newInstance() }
            factory { CommentsFragment.newInstance() }
            factory { WriteCommentFragment.newInstance() }
            factory { SearchFragment.newInstance() }
            factory { AboutFragment.newInstance() }
            factory { EditorialFragment.newInstance() }
            factory { DistributionFragment.newInstance() }
            factory { SubscriptionsFragment.newInstance() }
            factory { ContactUsFragment.newInstance() }
            factory { SettingsFragment.newInstance() }
            factory { FavouriteFragment.newInstance() }
        }
        val appModule = module {
            single {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    // .addCallback(Callback(coroutineScope, resources))
                    .build()
            }
        }

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            // declare modules
            modules(fragmentModule, appModule)
        }

        init = true
    }


}