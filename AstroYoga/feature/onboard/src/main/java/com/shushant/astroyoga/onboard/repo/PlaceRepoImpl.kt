package com.shushant.astroyoga.onboard.repo

import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.astroyoga.data.repo.PlaceRepository
import com.shushant.astroyoga.network.utils.Either
import com.shushant.astroyoga.network.utils.LOCATIONS
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRepoImpl(private val client: HttpClient) : PlaceRepository {
    override suspend fun getPlaces(searchTExt: String): Either<List<LocationSearchResultItem>> =
        withContext(Dispatchers.IO) {
            try {
                val result = client.get {
                    url {
                        url(LOCATIONS)
                        path("search.php")
                        parameters.append("q", searchTExt)
                        parameters.append("polygon_geojson", "1")
                        parameters.append("format", "jsonv2")
                    }
                }.body<List<LocationSearchResultItem>>()
                Either.success(result)
            } catch (e: Exception) {
                Either.error(e.message ?: "")
            }
        }
}