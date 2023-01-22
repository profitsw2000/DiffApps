package profitsw2000.diffapps.data.web

import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import io.reactivex.rxjava3.core.Single
import profitsw2000.diffapps.domain.Repository

class RepositoryImpl(private val api: KinoPoiskApi) : Repository {

    private val token = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"

    override fun getTopFilmsList(): Single<TopFilmsDTO> {
        return api.getTopFilmsList(token)
    }

    override fun getTopFilmsList(page: Int): Single<TopFilmsDTO> {
        return api.getTopFilmsListPage(page.toString(), token)
    }
}