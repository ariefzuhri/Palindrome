package com.ariefzuhri.suitmediainterntest.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersResponse(

    @Json(name = "per_page")
    val perPage: Int? = null,

    @Json(name = "total")
    val total: Int? = null,

    @Json(name = "data")
    val data: List<DataItem?>? = null,

    @Json(name = "page")
    val page: Int? = null,

    @Json(name = "total_pages")
    val totalPages: Int? = null,

    @Json(name = "support")
    val support: Support? = null,
) {

    @JsonClass(generateAdapter = true)
    data class DataItem(

        @Json(name = "last_name")
        val lastName: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "avatar")
        val avatar: String? = null,

        @Json(name = "first_name")
        val firstName: String? = null,

        @Json(name = "email")
        val email: String? = null,
    )

    @JsonClass(generateAdapter = true)
    data class Support(

        @Json(name = "text")
        val text: String? = null,

        @Json(name = "url")
        val url: String? = null,
    )
}