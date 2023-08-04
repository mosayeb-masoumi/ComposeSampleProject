package com.example.composesampleproject.features.home.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesampleproject.core.Resource
import com.example.composesampleproject.features.home.domain.usecase.GetListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val usecase: GetListUsecase): ViewModel() {


    //first way
    private val _todoList = mutableStateOf(ListState())
    val todoList : State<ListState> = _todoList

    // second way
//    private val _todoList = MutableStateFlow<ListState>(ListState())
//    val todoList = _todoList.asStateFlow()



    init {
        getTodoList()
    }

    fun getTodoList(){

        usecase().onEach { result ->
            when(result){

                is Resource.Loading -> {
                  _todoList.value = ListState(isLoading = true)
                }

                is Resource.Success -> {
                    _todoList.value = ListState(data = result.data!!)
                }

                is Resource.Error -> {
                    _todoList.value = ListState(error = "An unexpected error occured")
                }
            }

        }.launchIn(viewModelScope)
    }

}