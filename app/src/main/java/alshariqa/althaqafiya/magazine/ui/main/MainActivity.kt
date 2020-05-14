package alshariqa.althaqafiya.magazine.ui.main

import alshariqa.althaqafiya.magazine.R
import alshariqa.althaqafiya.magazine.base.BaseActivity
import alshariqa.althaqafiya.magazine.base.logD
import alshariqa.althaqafiya.magazine.ui.article.ArticleDetailFragment
import alshariqa.althaqafiya.magazine.ui.article.ArticlesFragment
import alshariqa.althaqafiya.magazine.ui.article.business.model.Article
import alshariqa.althaqafiya.magazine.ui.article.comments.CommentsFragment
import alshariqa.althaqafiya.magazine.ui.article.comments.WriteCommentFragment
import alshariqa.althaqafiya.magazine.ui.article.tab.ArticlesByAuthorFragment
import alshariqa.althaqafiya.magazine.ui.article.tab.ArticlesTabFragment
import alshariqa.althaqafiya.magazine.ui.category.CategoriesFragment
import alshariqa.althaqafiya.magazine.ui.category.CategoryDetailFragment
import alshariqa.althaqafiya.magazine.ui.category.business.model.Keys
import alshariqa.althaqafiya.magazine.ui.home.HomeFragment
import alshariqa.althaqafiya.magazine.ui.home.business.model.Author
import alshariqa.althaqafiya.magazine.ui.home.business.model.Magazine
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
import alshariqa.althaqafiya.magazine.utils.view.PopUpUtils
import alshariqa.althaqafiya.magazine.utils.view.handleOnBackPressed
import alshariqa.althaqafiya.magazine.utils.view.loadFragment
import alshariqa.althaqafiya.magazine.utils.view.scaleViewRelativeToCenter
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_bar.*
import kotlinx.android.synthetic.main.drawer.*
import kotlinx.android.synthetic.main.splash_layout.*
import kotlinx.android.synthetic.main.splash_layout.ivLogo
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity(), MainViewModel.View {
    val mMainViewModel: MainViewModel by viewModels()

    private lateinit var slidingRootNav: SlidingRootNav

    var onScreenTouchListener: OnScreenTouchListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel.let {
            it.attachView(this)
            it.addObservers(this)
            loadHomePage()
        }

        llSubjects.setOnClickListener {
            loadCategories()

        }

        llHome.setOnClickListener {
            loadHomePage()
        }

        llMagazines.setOnClickListener {
            loadMagazines()
        }

        llArticles.setOnClickListener {
            loadArticles()
        }




        initSlidingDrawer()

    }

    private fun initSlidingDrawer() {

        slidingRootNav = SlidingRootNavBuilder(this)
            .withMenuLayout(R.layout.drawer)
            .withGravity(SlideGravity.RIGHT)
            .inject()


        ivClose.setOnClickListener {
            onDrawerClicked(it)
        }

        ivLike.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadFavourite()
        }

        tvCategory.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadCategories()
        }

        tvMagazines.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadMagazines()
        }
        tvAbout.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadAboutUs()
        }
        tvEditorial.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadEditorial()

        }

        tvDistribution.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadDistribution()
        }

        tvSubscription.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadSubscription()
        }

        tvContactUs.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadContactUs()
        }
        tvSettings.setOnClickListener {
            onDrawerClicked(it)
            mMainViewModel.loadSettings()
        }

        ivApp1.setOnClickListener {
            openGooglePlay("com.alrafid")
        }

        ivApp2.setOnClickListener {
            openGooglePlay("ae.gov.sdci")
        }


    }

    private fun openGooglePlay(appPackageName: String) {

        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()

            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }
    }


    fun onDrawerClicked(v: View) {

        if (slidingRootNav.isMenuOpened)
            slidingRootNav.closeMenu(true)
        else slidingRootNav.openMenu(true)
        /* drawerL.run {
             if (isDrawerOpen(GravityCompat.END))
                 closeDrawer(GravityCompat.END)
             else openDrawer(GravityCompat.END)
         }*/
    }

    fun onSearchClicked(v: View) {

        mMainViewModel.loadSearch()
    }

    fun onSearchFilterClicked(v: View) {
        PopUpUtils.showFilterPopUp(v)
    }

    fun onBackClicked(v: View) {
        onBackPressed()
    }

    private fun toolBar(settings: Settings) {

        when (settings) {
            Settings.SEARCH -> {
                ivFilter.visibility = View.VISIBLE
                ivBack.visibility = View.VISIBLE
                ivSearch.visibility = View.INVISIBLE
                ivSideBar.visibility = View.INVISIBLE

            }
            Settings.ARTICLES -> {
                ivSearch.visibility = View.INVISIBLE
                ivSideBar.visibility = View.VISIBLE
                ivFilter.visibility = View.GONE
                ivBack.visibility = View.GONE
            }
            Settings.MAGAZINES -> {
                ivSearch.visibility = View.INVISIBLE
                ivSideBar.visibility = View.VISIBLE
                ivFilter.visibility = View.GONE
                ivBack.visibility = View.GONE
            }
            Settings.ABOUT_US -> {
                ivSearch.visibility = View.INVISIBLE
                ivSideBar.visibility = View.VISIBLE
                ivBack.visibility = View.VISIBLE
                ivFilter.visibility = View.GONE

            }

            else -> {
                ivFilter.visibility = View.GONE
                ivBack.visibility = View.GONE
                ivSearch.visibility = View.VISIBLE
                ivSideBar.visibility = View.VISIBLE

            }
        }

    }

    fun applySettings(settings: Settings) {
        toolBar(settings)
        disableBottomBarAt(settings)
    }

    private fun disableBottomBarAt(settings: Settings) {

        when (settings) {
            Settings.HOME -> {
                ivHome.setImageResource(R.drawable.ic_home_selected)
                ivArticle.setImageResource(R.drawable.ic_articles)
                ivSubjects.setImageResource(R.drawable.ic_subjects)
                ivMagazine.setImageResource(R.drawable.ic_magazines)
                tvHome.setTextColor(getResColor(R.color.colorAccent))
                tvArticle.setTextColor(getResColor(R.color.app_dark_gray))
                tvSubjects.setTextColor(getResColor(R.color.app_dark_gray))
                tvMagazine.setTextColor(getResColor(R.color.app_dark_gray))
                llHome.isEnabled = false
                llSubjects.isEnabled = true
                llMagazines.isEnabled = true
                llArticles.isEnabled = true
            }
            Settings.CATEGORIES -> {
                ivHome.setImageResource(R.drawable.ic_home)
                ivArticle.setImageResource(R.drawable.ic_articles)
                ivSubjects.setImageResource(R.drawable.ic_subjects_selected)
                ivMagazine.setImageResource(R.drawable.ic_magazines)
                tvHome.setTextColor(getResColor(R.color.app_dark_gray))
                tvArticle.setTextColor(getResColor(R.color.app_dark_gray))
                tvSubjects.setTextColor(getResColor(R.color.colorAccent))
                tvMagazine.setTextColor(getResColor(R.color.app_dark_gray))
                llSubjects.isEnabled = false
                llHome.isEnabled = true
                llMagazines.isEnabled = true
                llArticles.isEnabled = true
            }
            Settings.ARTICLES -> {
                ivArticle.setImageResource(R.drawable.ic_articles_selected)
                ivHome.setImageResource(R.drawable.ic_home)
                ivSubjects.setImageResource(R.drawable.ic_subjects)
                ivMagazine.setImageResource(R.drawable.ic_magazines)
                tvHome.setTextColor(getResColor(R.color.app_dark_gray))
                tvArticle.setTextColor(getResColor(R.color.colorAccent))
                tvSubjects.setTextColor(getResColor(R.color.app_dark_gray))
                tvMagazine.setTextColor(getResColor(R.color.app_dark_gray))
                llArticles.isEnabled = false
                llSubjects.isEnabled = true
                llHome.isEnabled = true
                llMagazines.isEnabled = true
            }
            Settings.MAGAZINES -> {
                ivArticle.setImageResource(R.drawable.ic_articles)
                ivHome.setImageResource(R.drawable.ic_home)
                ivSubjects.setImageResource(R.drawable.ic_subjects)
                ivMagazine.setImageResource(R.drawable.ic_magazines_selected)
                tvHome.setTextColor(getResColor(R.color.app_dark_gray))
                tvArticle.setTextColor(getResColor(R.color.app_dark_gray))
                tvSubjects.setTextColor(getResColor(R.color.app_dark_gray))
                tvMagazine.setTextColor(getResColor(R.color.colorAccent))
                llMagazines.isEnabled = false
                llArticles.isEnabled = true
                llSubjects.isEnabled = true
                llHome.isEnabled = true

            }
            Settings.ABOUT_US -> {

            }
            Settings.SEARCH -> {

            }
            else -> {

            }
        }
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun loadHomePage() {
        val homeFragment: HomeFragment by inject()
        loadFragment(homeFragment)
    }

    override fun loadAboutUs() {
        val aboutFragment: AboutFragment by inject()
        loadFragment(aboutFragment, true)
    }

    override fun loadEditorial() {
        val editorialFragment: EditorialFragment by inject()
        loadFragment(editorialFragment, true)
    }

    override fun loadDistribution() {
        val distributionFragment: DistributionFragment by inject()
        loadFragment(distributionFragment, true)
    }

    override fun loadSubscription() {
        val subscriptionsFragment: SubscriptionsFragment by inject()
        loadFragment(subscriptionsFragment, true)
    }

    override fun loadContactUs() {
        val contactUsFragment: ContactUsFragment by inject()
        loadFragment(contactUsFragment, true)
    }

    override fun loadSettings() {
        val settingsFragment: SettingsFragment by inject()
        loadFragment(settingsFragment, true)
    }

    override fun loadSearch() {
        val searchFragment: SearchFragment by inject()
        loadFragment(searchFragment, true)
    }

    override fun loadFavourite() {
        val favouriteFragment: FavouriteFragment by inject()
        loadFragment(favouriteFragment, true)
    }

    override fun loadCategories() {
        val categoriesFragment: CategoriesFragment by inject()
        loadFragment(categoriesFragment, true)
    }

    override fun loadCategoryDetails(categoryId: Int) {
        val categoryDetailFragment: CategoryDetailFragment by inject()
        loadFragment(categoryDetailFragment.also {
            it.arguments = Bundle().apply {
                putInt(Keys.CATEGORY_ID, categoryId)
            }
        }, true)
    }

    override fun loadArticles() {
        val articlesTabFragment: ArticlesTabFragment by inject()
        loadFragment(articlesTabFragment, true)
    }

    override fun loadArticles(authorId: Int) {
        val articlesFragment: ArticlesFragment by inject()
        loadFragment(articlesFragment.also {
            it.arguments = Bundle().apply {
                putInt(Keys.AUTHOR_ID, authorId)
            }
        }, true)
    }

    override fun loadArticlesByAuthor(author: Author) {
        val articlesByAuthorFragment: ArticlesByAuthorFragment by inject()
        loadFragment(articlesByAuthorFragment.also {
            it.arguments = Bundle().apply {
                putParcelable(Keys.AUTHOR_DETAIL, author)
            }
        }, true)
    }

    override fun loadArticleDetails(article: Article) {
        val articleDetailFragment: ArticleDetailFragment by inject()
        loadFragment(articleDetailFragment.also {
            it.arguments = Bundle().apply {
                putParcelable(Keys.ARTICLE_DETAIL, article)
            }
        }, true)

    }

    override fun loadAuthorArticleDetails(writer: Author, article: Article) {
        val articleDetailFragment: alshariqa.althaqafiya.magazine.ui.article.tab.ArticleDetailFragment by inject()
        loadFragment(articleDetailFragment.also {
            it.arguments = Bundle().apply {
                putParcelable(Keys.ARTICLE_DETAIL, article)
                putParcelable(Keys.AUTHOR_DETAIL, writer)
            }
        }, true)
    }

    override fun loadMagazines() {
        val magazineFragment: MagazineFragment by inject()
        loadFragment(magazineFragment, true)
    }

    override fun loadMagazine(magazine: Magazine) {
        val magazineDetailsFragment: MagazineDetailsFragment by inject()
        loadFragment(magazineDetailsFragment.also {
            it.arguments = Bundle().apply {
                putParcelable(Keys.MAGAZINE_ID, magazine)
            }
        }, true)
    }

    override fun loadComments() {
        val commentsFragment: CommentsFragment by inject()
        loadFragment(commentsFragment, true)
    }

    override fun writeComment() {
        val writeCommentFragment: WriteCommentFragment by inject()
        loadFragment(writeCommentFragment, true)
    }


    override fun showBottomBar(show: Boolean) {
        root.getChildAt(2).visibility = if (show) View.VISIBLE
        else View.GONE


    }

    override fun showToolBar(title: String?) {
        tb.visibility = if (title.isNullOrBlank()) View.GONE
        else View.VISIBLE

        tvToolbarTitle.text = title


        logD("ToolBarVisible", tb.isShown.toString())

    }

    override fun onSplashScreen() {
        layoutSplash.visibility = View.VISIBLE
        scaleViewRelativeToCenter(ivLogo, ivBrand) {
            layoutSplash.visibility = View.GONE
        }


    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        onScreenTouchListener?.onScreenTouched(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handleOnBackPressed()
    }
}
