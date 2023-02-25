package com.udacity.asteroidradar.api

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
data class NeoFeedResponse(
    val links: Links?,
    val elementCount: Int?,
    val near_earth_objects: Map<String, List<Asteroid>>?
): Parcelable
@Parcelize
data class Links(
    val next: String?="",
    val previous: String?="",
    val self: String?=""
): Parcelable
@Parcelize
data class Asteroid(
    val id: String?,
    val name: String?,
    var date:String?,
    val is_potentially_hazardous_asteroid:Boolean?,
    val closeApproachData: List<CloseApproachData?>?,
    val is_sentry_object:Boolean?,
    val absolute_magnitude_h:Double?,
    var relative_velocity:String?,
    var miss_distance:String?,
    val estimated_diameter:EstimatedDiameter?

):Parcelable
@Parcelize
data class EstimatedDiameter(
    val kilometers:kilometers?,
    val meters:kilometers?
):Parcelable
@Parcelize
data class kilometers(
    val estimated_diameter_min:Double?,
    val estimated_diameter_max:Double?
):Parcelable
@Parcelize
data class CloseApproachData(
    val closeApproachDate: String?,
    val missDistance: MissDistance?,
    val relative_velocity:RelativeVelocity?
): Parcelable
@Parcelize
data class RelativeVelocity(
    val kilometers_per_second:String?
):Parcelable
@Parcelize
data class MissDistance(
    val astronomical: String?,
    val lunar: String?,
    val kilometers: String?,
    val miles: String?
): Parcelable
