package alshariqa.althaqafiya.magazine.ui.navigation.favourite.business

import alshariqa.althaqafiya.magazine.base.BaseRepository

object FavouriteRepository : BaseRepository() {

    private val articlesDAO = appDatabase.getArticlesDAO()
    private val magazinesDAO = appDatabase.getMagazinesDAO()


    fun getLikedArticles() = articlesDAO.getLikedArticles()

    fun getFavoriteMagazines() = magazinesDAO.getFavoriteMagazines()
}