package com.example.composesampleproject.core.remote

import com.example.composesampleproject.features.home.data.dto.TodosModelDto
import com.example.composesampleproject.features.item_detail.data.dto.DetailModelDto
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("todos/")
    suspend fun getTodosList() : List<TodosModelDto>

//    @GET("todos/{id}")
//    suspend fun getDetailItem(@Query(value = "id") id:Int): TodosModelDto
    @GET("todos/{id}")
    suspend fun getDetailItem(@Path(value = "id") id:Int): DetailModelDto

    @GET("/fake/api")
    fun refreshToken(): Call<JsonObject?>?
}