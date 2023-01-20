package profitsw2000.diffapps.mappers

import Docs
import DocsDTO

class DocsMapper (private val posterMapper: PosterMapper,
                  private val ratingMapper: RatingMapper) {

    fun map(docsDTO: DocsDTO): Docs {
        return Docs(
            poster = posterMapper.map(docsDTO.poster),
            rating = ratingMapper.map(docsDTO.rating),
            id = docsDTO.id,
            name = docsDTO.name,
            year = docsDTO.year
        )
    }
}