package profitsw2000.diffapps.entity.topfilms

import profitsw2000.diffapps.entity.topfilms.Poster
import profitsw2000.diffapps.entity.topfilms.Rating

data class Docs (
    val poster: Poster,
    val rating: Rating,
    val id: Int,
    val name: String,
    val year: Int
)