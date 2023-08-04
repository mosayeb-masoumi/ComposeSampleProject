package com.example.composesampleproject.features.item_detail.data.dto

import com.example.composesampleproject.features.item_detail.domain.model.DetailModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailModelDto(
    @SerializedName("userId")
    @Expose
    val userId: Int,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("completed")
    @Expose
    val completed: Boolean
)

fun DetailModelDto.toDetailModel(): DetailModel {
    return DetailModel(
        userId = userId,
        id = id,
        title = title,
        completed = completed
    )
}