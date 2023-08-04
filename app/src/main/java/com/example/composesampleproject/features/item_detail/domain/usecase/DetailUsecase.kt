package com.example.composesampleproject.features.item_detail.domain.usecase

import com.example.composesampleproject.core.Resource
import com.example.composesampleproject.core.remote.ApiService
import com.example.composesampleproject.features.home.domain.model.TodosModel
import com.example.composesampleproject.features.item_detail.data.dto.toDetailModel
import com.example.composesampleproject.features.item_detail.domain.model.DetailModel
import com.example.composesampleproject.features.item_detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DetailUsecase @Inject constructor(private val detailRepository: DetailRepository) {

    operator fun invoke(itemId:Int): Flow<Resource<DetailModel>> = flow{

        try{
            emit(Resource.Loading<DetailModel>())
            val detailModel = detailRepository.getDetailItem(itemId).toDetailModel()
            emit(Resource.Success<DetailModel>(detailModel))

        }catch (e: HttpException){
            emit(Resource.Error<DetailModel>(e.localizedMessage ?: "an unexpected error occured", null))
        }catch (e: IOException){
            emit(Resource.Error<DetailModel>(e.localizedMessage ?: "check your internet connection", null))
        }
    }
}