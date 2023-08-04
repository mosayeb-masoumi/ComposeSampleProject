package com.example.composesampleproject.features.home.data

import com.example.composesampleproject.core.remote.ApiService
import com.example.composesampleproject.features.home.data.dto.TodosModelDto
import com.example.composesampleproject.features.home.domain.repository.HomeRepository
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(private val apiService: ApiService) : HomeRepository {

    override suspend fun getTodosList(): List<TodosModelDto> {
        return apiService.getTodosList()
    }

//    override suspend fun getDetailItem(id: Int): TodosModelDto {
//        return apiService.getDetailItem(id)
//    }
}