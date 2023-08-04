package com.example.composesampleproject.features.item_detail.data

import com.example.composesampleproject.core.remote.ApiService
import com.example.composesampleproject.features.item_detail.data.dto.DetailModelDto
import com.example.composesampleproject.features.item_detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private  val apiService: ApiService):DetailRepository{

    override suspend fun getDetailItem(id: Int): DetailModelDto {
        return  apiService.getDetailItem(id)
    }
}