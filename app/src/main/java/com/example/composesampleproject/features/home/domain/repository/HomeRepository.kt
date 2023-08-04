package com.example.composesampleproject.features.home.domain.repository

import com.example.composesampleproject.features.home.data.dto.TodosModelDto
import retrofit2.http.Query

interface HomeRepository {

    suspend fun getTodosList() : List<TodosModelDto>
//    suspend fun getDetailItem(@Query(value = "id") id:Int): TodosModelDto
}