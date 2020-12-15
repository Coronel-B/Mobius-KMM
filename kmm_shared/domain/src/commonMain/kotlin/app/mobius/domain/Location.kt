package app.mobius.domain

import com.benasher44.uuid.Uuid

class Location(
    val uuid: Uuid?,
    val country: Country,
    val province: Province,
    val city: City,
    val direction: Direction? = null
) 

class Country(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
) 


class Province(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
) 

class City(
    val uuid: Uuid?,
    val name: String,
    val locationLimits: LocationLimits,
)

class LocationLimits(
    val uuid: Uuid?,
    val northeast: Coordinate,
    val southwest: Coordinate
)

class Direction(
    val uuid: Uuid?,
    val street: String,
    val number: Int,
    val firstBetweenStreet: String? = null,
    val secondBetweenStreet: String? = null,
    val coordinate: Coordinate
)

class Coordinate(
    val uuid: Uuid?,
    val lat: Double,
    val lng: Double,
) 