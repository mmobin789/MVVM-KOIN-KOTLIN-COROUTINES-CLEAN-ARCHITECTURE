package alshariqa.althaqafiya.magazine.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {
    private const val baseURL = "http://alshariqa-althaqafiya.ae:8889/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val homeService: HomeService = retrofit.create(HomeService::class.java)

    val categoryService: CategoryService = retrofit.create(CategoryService::class.java)

    val articleService: ArticleService = retrofit.create(ArticleService::class.java)

    val magazineService: MagazineService = retrofit.create(MagazineService::class.java)

    val navigationService: NavigationService = retrofit.create(NavigationService::class.java)
}