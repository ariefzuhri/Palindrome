package com.ariefzuhri.suitmediainterntest.data.model

import android.os.Parcelable
import com.ariefzuhri.suitmediainterntest.data.source.remote.response.UsersResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val email: String,
    val name: String,
    val avatar: String,
) : Parcelable

fun UsersResponse.DataItem?.toUser(): User {
    return User(
        id = this?.id ?: -1,
        email = this?.email.orEmpty(),
        name = "${this?.firstName} ${this?.lastName}",
        avatar = this?.avatar.orEmpty()
    )
}