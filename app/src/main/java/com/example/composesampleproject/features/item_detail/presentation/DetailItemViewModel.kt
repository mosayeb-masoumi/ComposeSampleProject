package com.example.composesampleproject.features.item_detail.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesampleproject.core.Constants
import com.example.composesampleproject.core.Resource
import com.example.composesampleproject.features.item_detail.domain.usecase.DetailUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailItemViewModel @Inject constructor(
    private val detailUsecase: DetailUsecase,
    private val savedStateHandle: SavedStateHandle
    ): ViewModel() {

    private val _detailResult = mutableStateOf(DetailState())
    val detailResult : State<DetailState> = _detailResult

//    init {
//        // Retrieve the savedIntValue from SavedStateHandle
//        val savedIntValue: Int = savedStateHandle.get("intValue") ?: 1
//        println("Retrieved intValue from SavedStateHandle: $savedIntValue")
//        getItemDetail(savedIntValue)
//    }

//    var transferredValue: Int = 0
//    init {
//        // Access transferredValue in the init block
//        println("Transferred value: $transferredValue")
//    }

//    init {
//        savedStateHandle.get<Int>(Constants.DETAIL_ITEM_ID)?.let { itemId ->
//            getItemDetail(itemId)
//        }
//    }

    fun setItemId(value: Int) {
        getItemDetail(value)
        // Perform any additional logic if needed
    }

    fun getItemDetail(id:Int){

        detailUsecase(id).onEach { result ->

            when(result){

                is Resource.Loading -> {
                    _detailResult.value = DetailState(isLoading = true)
                }

                is Resource.Success -> {
                    _detailResult.value = DetailState(data = result.data)
                }

                is Resource.Error -> {
                    _detailResult.value = DetailState(error = "An unexpected error occured")
                }
            }

        }.launchIn(viewModelScope)
    }
}