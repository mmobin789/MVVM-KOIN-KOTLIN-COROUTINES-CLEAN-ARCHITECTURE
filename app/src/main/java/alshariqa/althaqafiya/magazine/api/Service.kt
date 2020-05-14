package alshariqa.althaqafiya.magazine.api

import alshariqa.althaqafiya.magazine.ui.article.business.model.ArticleDetails
import alshariqa.althaqafiya.magazine.ui.article.business.model.Articles
import alshariqa.althaqafiya.magazine.ui.category.business.model.Categories
import alshariqa.althaqafiya.magazine.ui.home.business.model.Home
import alshariqa.althaqafiya.magazine.ui.magazine.business.model.Magazines
import alshariqa.althaqafiya.magazine.ui.navigation.about.business.model.AboutUs
import alshariqa.althaqafiya.magazine.ui.navigation.contact.business.model.ContactInfo
import alshariqa.althaqafiya.magazine.ui.navigation.distribution.business.model.Distribution
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NavigationService {
    @GET("Home/GetDistributors ")
    fun getDistributors(): Call<Distribution>

    @GET("Home/GetContactUs")
    fun getContactUsPage(): Call<ContactInfo>

    @GET("Home/GetAboutUs ")
    suspend fun getAboutUs(): AboutUs

    @GET("Home/GetEditorialBoard ")
    suspend fun getEditorial(): AboutUs
}

interface HomeService {
    @GET("Home/GetHomeData")
    fun getHomePage(): Call<Home>
}

interface CategoryService {
    @GET("Home/GetAllCategoriesWithArticles")
    fun getCategoriesWithArticles(@Query("CategoryId") categoryId: Int): Call<Categories>
}

interface ArticleService {
    @GET("Home/GetArticlesByAuthor")
    fun getArticlesByAuthorId(@Query("AuthorId") authorId: Int): Call<Articles>

    @GET("home/GetArticleDetail")
    fun getArticleDetails(@Query("Id") articleId: Int): Call<ArticleDetails>

    @GET("home/GetAuthorsWithArticleCount")
    fun getAuthors(): Call<Articles>

}

interface MagazineService {
    @GET("Home/GetAllMagazinesWithDetail")
    fun getMagazines(
        @Query("currentPage") page: Int,
        @Query("Year") year: Int,
        @Query("Month") month: Int,
        @Query("Id") id: Int = 0
    ): Call<Magazines>
}