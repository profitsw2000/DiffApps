package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.topfilms.TopFilms
import profitsw2000.diffapps.model.topfilms.TopFilmsDTO

class TopFilmsMapper (private val docsMapper: DocsMapper){
    fun map(topFilmsDTO: TopFilmsDTO): TopFilms {
        return TopFilms(
            docs = topFilmsDTO.docs.map { docsMapper.map(it) },
            page = topFilmsDTO.page,
            pages = topFilmsDTO.pages
        )
    }
}