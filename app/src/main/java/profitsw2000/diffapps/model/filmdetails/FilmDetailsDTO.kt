package profitsw2000.diffapps.model.filmdetails


import com.google.gson.annotations.SerializedName
import profitsw2000.diffapps.model.topfilms.*

data class FilmDetailsDTO(
    @SerializedName("ageRating")
    val ageRating: Int,
    @SerializedName("alternativeName")
    val alternativeName: String,
    @SerializedName("backdrop")
    val backdrop: BackdropDTO,
    @SerializedName("budget")
    val budget: BudgetDTO,
    @SerializedName("countries")
    val countries: List<CountryDTO>,
    @SerializedName("createDate")
    val createDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("distributors")
    val distributors: DistributorsDTO,
    @SerializedName("externalId")
    val externalId: ExternalIdDTO,
    @SerializedName("facts")
    val facts: List<FactDTO>,
    @SerializedName("fees")
    val fees: FeesDTO,
    @SerializedName("genres")
    val genres: List<GenreDTO>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ImagesDTO,
    @SerializedName("imagesInfo")
    val imagesInfo: ImagesInfoDTO,
    @SerializedName("logo")
    val logo: LogoDTO,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<NameDTO>,
    @SerializedName("persons")
    val persons: List<PersonDTO>,
    @SerializedName("poster")
    val poster: PosterDTO,
    @SerializedName("premiere")
    val premiere: PremiereDTO,
    @SerializedName("productionCompanies")
    val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("rating")
    val rating: RatingDTO,
    @SerializedName("ratingMpaa")
    val ratingMpaa: String,
    @SerializedName("seasonsInfo")
    val sequelsAndPrequels: List<SequelsAndPrequelDTO>,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("slogan")
    val slogan: String,
    @SerializedName("spokenLanguages")
    val spokenLanguages: List<SpokenLanguageDTO>,
    @SerializedName("status")
    val status: String,
    @SerializedName("technology")
    val technology: TechnologyDTO,
    @SerializedName("ticketsOnSale")
    val ticketsOnSale: Boolean,
    @SerializedName("top250")
    val top250: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("typeNumber")
    val typeNumber: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("videos")
    val videos: VideosDTO,
    @SerializedName("votes")
    val votes: VotesDTO,
    @SerializedName("watchability")
    val watchability: WatchabilityDTO,
    @SerializedName("year")
    val year: Int
)