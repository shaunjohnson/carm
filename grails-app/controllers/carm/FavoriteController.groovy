package carm

class FavoriteController {

    def favoriteService

    def index() { }

    def ajaxAddToFavorites() {
        favoriteService.addToFavorites(params.id)
        render ""
    }

    def ajaxRemoveFromFavorites() {
        favoriteService.removeFromFavorites(params.id)
        render ""
    }
}
