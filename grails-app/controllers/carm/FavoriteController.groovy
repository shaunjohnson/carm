package carm

class FavoriteController {

    def favoriteService

    def index() { }

    def ajaxAddToFavorites() {
        favoriteService.addToCurrentUserFavorites(params.id)
        render ""
    }

    def ajaxRemoveFromFavorites() {
        favoriteService.removeFromCurrentUserFavorites(params.id)
        render ""
    }
}
