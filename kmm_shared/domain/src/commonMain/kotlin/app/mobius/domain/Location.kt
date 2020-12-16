package app.mobius.domain

import com.benasher44.uuid.Uuid

data class Location(
    val uuid: Uuid? = null,
    val country: Country,
    val province: Province,
    val city: City,
    val direction: Direction? = null
) {
    constructor() : this(country = Country(), province = Province(), city = City())
}

data class Country(
    val uuid: Uuid? = null,
    val name: String,
    val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}


data class Province(
    val uuid: Uuid? = null,
    val name: String,
    val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

data class City(
    val uuid: Uuid? = null,
    val name: String,
    val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

data class LocationLimits(
    val uuid: Uuid? = null,
    val northeast: Coordinate,
    val southwest: Coordinate
) {
    constructor() : this(northeast = Coordinate(), southwest = Coordinate())
}

data class Direction(
    val uuid: Uuid? = null,
    val street: String,
    val number: Int,
    val firstBetweenStreet: String? = null,
    val secondBetweenStreet: String? = null,
    val coordinate: Coordinate
)

data class Coordinate(
    val uuid: Uuid? = null,
    val lat: Double,
    val lng: Double,
) {
    constructor() : this(lat = 0.0, lng = 0.0)
}