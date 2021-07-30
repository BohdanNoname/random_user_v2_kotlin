package com.nedashkivskyi.randomuser.pojo

import android.os.Parcelable
import androidx.room.*
import com.nedashkivskyi.randomuser.utils.Constants
import com.nedashkivskyi.randomuser.utils.IntConverter
import java.io.Serializable

data class PersonResponse(
    var info: Info?,
    var results: List<Result>
)

data class Info(
    var page: Int,
    var results: Int,
    var seed: String,
    var version: String
)

@kotlinx.serialization.Serializable
@Entity(tableName = Constants.TABLE_NAME)
data class Result(
    @PrimaryKey(autoGenerate = true) var _id: Int,
    @ColumnInfo(name = "gender", defaultValue = "null") var gender: String?,
    @Embedded var name: Name,
    @Embedded var location: Location,
    @ColumnInfo(name = "email", defaultValue = "null") var email: String?,
    @Embedded var login: Login,
    @Embedded var dob: Dob,
    @Embedded var registered: Registered,
    @ColumnInfo(name = "phone", defaultValue = "null") var phone: String?,
    @ColumnInfo(name = "cell", defaultValue = "null") var cell: String?,
    @Embedded var id: Id,
    @Embedded var picture: Picture,
    @ColumnInfo(name = "nat", defaultValue = "null") var nat: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (gender != other.gender) return false
        if (name != other.name) return false
        if (location != other.location) return false
        if (email != other.email) return false
        if (login != other.login) return false
        if (dob != other.dob) return false
        if (registered != other.registered) return false
        if (phone != other.phone) return false
        if (cell != other.cell) return false
        if (id != other.id) return false
        if (picture != other.picture) return false
        if (nat != other.nat) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gender?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + login.hashCode()
        result = 31 * result + dob.hashCode()
        result = 31 * result + registered.hashCode()
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (cell?.hashCode() ?: 0)
        result = 31 * result + id.hashCode()
        result = 31 * result + picture.hashCode()
        result = 31 * result + (nat?.hashCode() ?: 0)
        return result
    }
}

@kotlinx.serialization.Serializable
data class Dob(
    @ColumnInfo(name = "age", defaultValue = "null") var age: Int?,
    @ColumnInfo(name = "date", defaultValue = "null") var date: String?
)

@kotlinx.serialization.Serializable
data class Id(
    @ColumnInfo(name = "id_name", defaultValue = "null") var name: String?,
    @ColumnInfo(name = "id_value", defaultValue = "null") var value: String?
)

@kotlinx.serialization.Serializable
data class Location(
    @ColumnInfo(name = "city", defaultValue = "null") var city: String?,
    @Embedded var coordinates: Coordinates,
    @ColumnInfo(name = "country", defaultValue = "null") var country: String?,
    @ColumnInfo(name = "postcode", defaultValue = "null")
    @TypeConverters(IntConverter::class) var postcode: String?,
    @ColumnInfo(name = "state", defaultValue = "null") var state: String?,
    @Embedded var street: Street,
    @Embedded var timezone: Timezone
)

@kotlinx.serialization.Serializable
data class Login(
    @ColumnInfo(name = "md5", defaultValue = "null") var md5: String?,
    @ColumnInfo(name = "password", defaultValue = "null") var password: String?,
    @ColumnInfo(name = "salt", defaultValue = "null") var salt: String?,
    @ColumnInfo(name = "sha1", defaultValue = "null") var sha1: String?,
    @ColumnInfo(name = "sha256", defaultValue = "null") var sha256: String?,
    @ColumnInfo(name = "username", defaultValue = "null") var username: String?,
    @ColumnInfo(name = "uuid", defaultValue = "null") var uuid: String?
)

@kotlinx.serialization.Serializable
data class Name(
    @ColumnInfo(name = "first", defaultValue = "null") var first: String?,
    @ColumnInfo(name = "last", defaultValue = "null") var last: String?,
    @ColumnInfo(name = "title", defaultValue = "null") var title: String?
)

@kotlinx.serialization.Serializable
data class Picture(
    @ColumnInfo(name = "large", defaultValue = "null") var large: String?,
    @ColumnInfo(name = "medium", defaultValue = "null") var medium: String?,
    @ColumnInfo(name = "thumbnail", defaultValue = "null") var thumbnail: String?
)

@kotlinx.serialization.Serializable
data class Registered(
    @ColumnInfo(name = "registered_age", defaultValue = "null") var age: Int?,
    @ColumnInfo(name = "registered_date", defaultValue = "null") var date: String?
)

@kotlinx.serialization.Serializable
data class Coordinates(
    @ColumnInfo(name = "latitude", defaultValue = "null") var latitude: String?,
    @ColumnInfo(name = "longitude", defaultValue = "null") var longitude: String?
)

@kotlinx.serialization.Serializable
data class Street(
    @ColumnInfo(name = "street_name", defaultValue = "null") var name: String?,
    @ColumnInfo(name = "number", defaultValue = "null") var number: Int?
)

@kotlinx.serialization.Serializable
data class Timezone(
    @ColumnInfo(name = "description", defaultValue = "null") var description: String?,
    @ColumnInfo(name = "offset", defaultValue = "null") var offset: String?
)