package com.shushant.astroyoga.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class LocationSearchResultItem(
    @SerialName("boundingbox")
    val boundingbox: List<String?>? = null,
    @SerialName("category")
    val category: String? = null, // boundary
    @SerialName("display_name")
    val displayName: String? = null, // Agra, Uttar Pradesh, India
    @SerialName("icon")
    val icon: String? = null, // https://nominatim.openstreetmap.org/ui/mapicons/poi_boundary_administrative.p.20.png
    @SerialName("importance")
    val importance: Double? = null, // 0.6776523904663649
    @SerialName("lat")
    val lat: String? = null, // 27.1752554
    @SerialName("licence")
    val licence: String? = null, // Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright
    @SerialName("lon")
    val lon: String? = null, // 78.0098161
    @SerialName("osm_id")
    val osmId: Long? = null, // 6074599273
    @SerialName("osm_type")
    val osmType: String? = null, // relation
    @SerialName("place_id")
    val placeId: Int? = null, // 299366268
    @SerialName("place_rank")
    val placeRank: Int? = null, // 12
    @SerialName("type")
    val type: String? = null // administrative
) {
    val getPlaceName = displayName?.split(",")?.get(0) ?: ""
}