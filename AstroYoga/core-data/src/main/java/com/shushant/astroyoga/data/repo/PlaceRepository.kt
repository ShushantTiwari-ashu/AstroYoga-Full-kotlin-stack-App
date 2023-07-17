package com.shushant.astroyoga.data.repo

import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.astroyoga.network.utils.Either

interface PlaceRepository {
    suspend fun getPlaces(searchTExt: String): Either<List<LocationSearchResultItem>>
}