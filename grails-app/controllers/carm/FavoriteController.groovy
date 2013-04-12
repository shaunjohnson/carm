package carm

class FavoriteController {
    def favoriteService

    def index() { }

    def ajaxAddApplicationToFavorites() {
        favoriteService.addApplicationToCurrentUserFavorites(params.id)
        render ""
    }

    def ajaxAddProjectToFavorites() {
        favoriteService.addProjectToCurrentUserFavorites(params.id)
        render ""
    }

    def ajaxRemoveApplicationFromFavorites() {
        favoriteService.removeApplicationFromCurrentUserFavorites(params.id)
        render ""
    }

    def ajaxRemoveProjectFromFavorites() {
        favoriteService.removeProjectFromCurrentUserFavorites(params.id)
        render ""
    }
}
