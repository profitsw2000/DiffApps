package profitsw2000.diffapps.model.topfilms

import com.google.gson.annotations.SerializedName

data class DocsDTO (
  @SerializedName("externalId") val externalId: ExternalIdDTO,
  @SerializedName("logo") val logo: LogoDTO,
  @SerializedName("poster") val poster: PosterDTO,
  @SerializedName("rating") val rating: RatingDTO,
  @SerializedName("votes") val votes: VotesDTO,
  @SerializedName("watchability") val watchability: WatchabilityDTO,
  @SerializedName("id") val id: Int,
  @SerializedName("names") val names: ArrayList<NamesDTO>,
  @SerializedName("alternativeName") val alternativeName: String,
  @SerializedName("description") val description: String,
  @SerializedName("enName") val enName: String,
  @SerializedName("movieLength") val movieLength: Int,
  @SerializedName("name") val name: String,
  @SerializedName("shortDescription") val shortDescription: String,
  @SerializedName("type") val type: String,
  @SerializedName("year") val year: Int,
  @SerializedName("releaseYears") val releaseYears: ArrayList<ReleaseYearsDTO>
)