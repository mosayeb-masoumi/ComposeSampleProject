package com.example.composesampleproject.features.item_detail.domain.repository

import com.example.composesampleproject.features.home.data.dto.TodosModelDto
import com.example.composesampleproject.features.item_detail.data.dto.DetailModelDto
import retrofit2.http.Query

interface DetailRepository {

    suspend fun getDetailItem(@Query(value = "id") id:Int): DetailModelDto
}