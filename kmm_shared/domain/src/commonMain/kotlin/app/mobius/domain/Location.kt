package app.mobius.domain

import com.benasher44.uuid.Uuid

data class Location(
    val uuid: Uuid?,
    val country: Country,
    val province: Province,
    val city: City,
    val direction: Direction? = null
) 

data class Country(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
) 


data class Province(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
) 

data class City(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
)

data class LocationLimits(
    val uuid: Uuid?,
    val northeast: Coordinate,
    val southwest: Coordinate
)

data class Direction(
    val uuid: Uuid?,
    val street: String,
    val number: Int,
    val firstBetweenStreet: String? = null,
    val secondBetweenStreet: String? = null,
    val coordinate: Coordinate
)

data class Coordinate(
    val uuid: Uuid?,
    val lat: Double,
    val lng: Double,
) 