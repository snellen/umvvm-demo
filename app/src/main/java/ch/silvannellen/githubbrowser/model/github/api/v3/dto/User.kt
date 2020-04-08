package ch.silvannellen.githubbrowser.model.github.api.v3.dto

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)