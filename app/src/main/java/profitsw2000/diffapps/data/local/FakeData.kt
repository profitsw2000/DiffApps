package profitsw2000.diffapps.data.local

import profitsw2000.diffapps.model.topfilms.DocsDTO
import profitsw2000.diffapps.model.topfilms.ExternalIdDTO
import profitsw2000.diffapps.model.topfilms.LogoDTO
import profitsw2000.diffapps.model.topfilms.NamesDTO
import profitsw2000.diffapps.model.topfilms.PosterDTO
import profitsw2000.diffapps.model.topfilms.RatingDTO
import profitsw2000.diffapps.model.topfilms.ReleaseYearsDTO
import profitsw2000.diffapps.model.topfilms.TopFilmsDTO
import profitsw2000.diffapps.model.topfilms.VotesDTO
import profitsw2000.diffapps.model.topfilms.WatchabilityDTO

val watchabilityDTO = WatchabilityDTO("")
val votesDTO = VotesDTO(0, 0, 0, 0, 0.0, "")
val releaseYearsDTO = ReleaseYearsDTO(0, 0, "")
val ratingDTO = RatingDTO(kp = 7.7, 0.0, 0, 0.0, 0.0, "")
val posterDTO = PosterDTO("", "", "")
val namesDTO = NamesDTO("", "")
val logoDTO = LogoDTO("", "")
val externalIdDTO = ExternalIdDTO("", "", 0, "")
val docsDTO = DocsDTO(
    externalId = externalIdDTO,
    logo = logoDTO,
    poster = posterDTO,
    rating = ratingDTO,
    votes = votesDTO,
    watchability = watchabilityDTO,
    id = 10,
    names = arrayListOf(namesDTO),
    alternativeName = "",
    description = "",
    enName = "",
    movieLength = 30,
    name = "Resurrection",
    shortDescription = "",
    type = "",
    year = 2022,
    releaseYears = arrayListOf(releaseYearsDTO)
)
val docsDTOList = arrayListOf<DocsDTO>(docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO, docsDTO)

fun getFakeTopFilmsList() : TopFilmsDTO {
    return TopFilmsDTO(
        docs = docsDTOList,
        total = 5,
        limit = 10,
        page = 1,
        pages = 10
    )
}