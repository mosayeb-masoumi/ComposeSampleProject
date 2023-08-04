package com.example.composesampleproject.features.home.domain.usecase

import com.example.composesampleproject.core.Resource
import com.example.composesampleproject.features.home.data.dto.toTodoModel
import com.example.composesampleproject.features.home.domain.model.TodosModel
import com.example.composesampleproject.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListUsecase @Inject constructor(private val homeRepository: HomeRepository) {

    operator fun invoke() : Flow<Resource<List<TodosModel>>> = flow {

        try{
            emit(Resource.Loading<List<TodosModel>>())
            val todoList =homeRepository.getTodosList().filter { it.id <10 }.map { it.toTodoModel() }
            emit(Resource.Success<List<TodosModel>>(todoList))

        }catch (e: HttpException){
            emit(Resource.Error<List<TodosModel>>(e.localizedMessage ?: "an unexpected error occured", null))
        }catch (e: IOException){
            emit(Resource.Error<List<TodosModel>>(e.localizedMessage ?: "check your internet connection", null))
        }
    }
}

//class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository){
//    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
//
//        try {
//            emit(Resource.Loading<List<Coin>>())
//            val coins = repository.getCoins().map { it.toCoin() }
//            emit(Resource.Success<List<Coin>>(coins))
//
//        }catch (e: HttpException){
//            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "an unexpected error occured"))
//        }catch (e: IOException){
//            emit(Resource.Error<List<Coin>>("check your internet connection"))
//        }
//    }
//}