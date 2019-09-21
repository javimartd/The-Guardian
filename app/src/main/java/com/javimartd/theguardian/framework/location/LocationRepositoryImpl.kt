package com.javimartd.theguardian.framework.location

import com.javimartd.theguardian.domain.entities.Location
import com.javimartd.theguardian.domain.repositories.LocationRepository
import java.util.*

class LocationRepositoryImpl: LocationRepository {

    //TODO fake location. Implement with Google API
    override fun getDeviceLocation(): Location {
        return Location(40.368374,-3.6845255, Date())
    }
}