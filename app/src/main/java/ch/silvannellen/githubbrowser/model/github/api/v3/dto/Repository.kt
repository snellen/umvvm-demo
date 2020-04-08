package ch.silvannellen.githubbrowser.model.github.api.v3.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class Repository(val id: Long, val name: String, val language: String?, val fork: Boolean, @SerializedName("updated_at") val updatedAt: Date)