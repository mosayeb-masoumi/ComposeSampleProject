package com.example.composesampleproject.features.home.data.dto

import com.example.composesampleproject.features.home.domain.model.TodosModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TodosModelDto(
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

fun TodosModelDto.toTodoModel() : TodosModel{

    return TodosModel(
        userId = userId,
        id = id,
        title = title
    )
}
